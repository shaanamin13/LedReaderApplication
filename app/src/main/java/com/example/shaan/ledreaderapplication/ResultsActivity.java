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
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;

public class ResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final TextView testBin = (TextView) findViewById(R.id.test_bin);
        final TextView testStr = (TextView) findViewById(R.id.test_str);


        Button button2 = (Button) findViewById(R.id.gen_results_btn);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {

                    analyzeFrames();

                    testStr.setText("TBD");

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        String binaryOutput = "";
        List<Integer> RGBFrameAvgs = new ArrayList<Integer>();
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;

        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/led/LEDAnalysis.mp4");
            grabber.setFormat("mp4");
            grabber.start();

            frameLength = grabber.getLengthInFrames();
            System.out.println("frameLength: "+ frameLength);
            for (int i = 0; i < .9*frameLength; i++) {
                System.out.println("i: "+ i);
                imgs = grabber.grab();

                int height = imgs.height();

                int width = imgs.width();
                _imgs = opencv_core.IplImage.create(width, height, opencv_core.IPL_DEPTH_8U, 4);

                bitmap = Bitmap.createBitmap(width, height,
                        Bitmap.Config.ARGB_8888);
                cvCvtColor(imgs, _imgs, opencv_imgproc.CV_BGR2RGBA);
                bitmap.copyPixelsFromBuffer(_imgs.getByteBuffer());


                int intColor = 0;
                int xMiddle = bitmap.getWidth() / 2;
                int yMiddle = bitmap.getHeight() / 2;

                int xPtr = xMiddle - 55;
                int yPtr = yMiddle - 55;
                int xEnd = xMiddle + 55;
                int yEnd = yMiddle + 55;
                int rTotal = 0;
                int gTotal = 0;
                int bTotal = 0;
                int rgbAvg = 0;


                for (int m = xPtr; m < xEnd+ 1; m++) {
                    for (int n = yPtr; n < yEnd + 1; n++) {

                        intColor = bitmap.getPixel(m, n);
                        int r = (intColor >> 16) & 0xff;
                        int g = (intColor >> 8) & 0xff;
                        int b = intColor & 0xff;

                        rTotal = rTotal + r;
                        gTotal = gTotal + g;
                        bTotal = bTotal + b;


                    }

                }

                int rAvg = rTotal / 12100;
                int gAvg = gTotal / 12100;
                int bAvg = bTotal / 12100;
                rgbAvg = (rAvg + gAvg + bAvg) / 3;
                System.out.println(" rgbAvg:  " + rgbAvg);
                RGBFrameAvgs.add(rgbAvg);


            }


            grabber.stop();
            grabber.release();
            System.out.println(" SUp");
            double threshold = calculateAverage(RGBFrameAvgs);
            binaryOutput = findBinary(RGBFrameAvgs, threshold);


            final TextView testBin = (TextView) findViewById(R.id.test_bin);
            testBin.setText(binaryOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private double calculateAverage(List<Integer> Values) {
        Integer sum = 0;
        if (!Values.isEmpty()) {
            for (Integer Value : Values) {
                sum += Value;
            }
            return sum.doubleValue() / Values.size();
        }
        return sum;
    }

    private String findBinary(List<Integer> Values, double threshold) {
        String result = "";


        for (int i = 0; i < Values.size(); i++) {
            System.out.println(Values.get(i));
            if (Values.get(i) > threshold + threshold*.20) {
                result = result + "1";
            } else {
                result = result + "0";
            }
        }


        return result;

    }


}