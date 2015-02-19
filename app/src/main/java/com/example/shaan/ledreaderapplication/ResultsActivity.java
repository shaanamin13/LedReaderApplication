package com.example.shaan.ledreaderapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;

public class ResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final TextView testBin = (TextView) findViewById(R.id.test_bin);
        final TextView testStr = (TextView) findViewById(R.id.test_str);


        Button button1 = (Button) findViewById(R.id.gen_imgs_btn);
        Button button2 = (Button) findViewById(R.id.gen_results_btn);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {

                    analyzeFrames();

                    testStr.setText("TBD");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void analyzeFrames() throws Exception, IOException {
        int counter = 0;
        int frameLength = 0;
        opencv_core.IplImage imgs;
        opencv_core.IplImage _imgs;
        Bitmap bitmap = null;
        FileOutputStream out = null;

        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/led/LEDAnalysis.mp4");
            grabber.setFormat("mp4");
            grabber.start();

            frameLength = grabber.getLengthInFrames();

            for (int i = 0; i < frameLength; i++) {
                //ImageIO.write(g.grab().getBufferedImage(), "png", new File("video-frame-" + System.currentTimeMillis() + ".png"));
                imgs = grabber.grab();
                int height = imgs.height();
                int width = imgs.width();
                _imgs = opencv_core.IplImage.create(width, height, opencv_core.IPL_DEPTH_8U, 4);

                bitmap = Bitmap.createBitmap(width, height,
                        Bitmap.Config.ARGB_8888);
                cvCvtColor(imgs, _imgs, opencv_imgproc.CV_BGR2RGBA);
                bitmap.copyPixelsFromBuffer(_imgs.getByteBuffer());
                out = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/led/i-" + counter + ".png");
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.close();
                counter++;
            }


            grabber.stop();
            grabber.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        analyzePNG(frameLength);


    }

    public void analyzePNG(int frameLength) {
        int counter = 1;
        String binaryOutput = "";
        Bitmap mBitmap;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;

        for (int i = 0; i <25; i++) {
            mBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/led/i-" + (counter-1) + ".png", opt);

            int intColor = 0;
            int x = mBitmap.getWidth() / 2;
            int y = mBitmap.getHeight() / 2;
            intColor = mBitmap.getPixel(x, y);

            int r = (intColor >> 16) & 0xff;
            int g = (intColor >> 8) & 0xff;
            int b = intColor & 0xff;

           System.out.println("Index Color value: " + intColor);
           System.out.println("Red: " + r);
           System.out.println("Green: " + g);
           System.out.println("Blue: " + b);

            System.out.println("Final String: " + binaryOutput);

            if (r >200) {

                    binaryOutput = binaryOutput + "1";

                }
             else {

                binaryOutput = binaryOutput + "0";

            }
            counter++;

        }
        System.out.println("Final String: " + binaryOutput);
        final TextView testBin = (TextView) findViewById(R.id.test_bin);
        testBin.setText(binaryOutput);

    }
}