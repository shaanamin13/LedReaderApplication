package com.example.shaan.ledreaderapplication;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Bitmap;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacpp.opencv_core;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        try {
            analyzeFrames();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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



    public void analyzeFrames() throws Exception,IOException{

       int counter = 0;
       int frameLength = 0;

            FFmpegFrameGrabber g = new FFmpegFrameGrabber(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/LEDAnalysis.mp4");
            g.start();

            opencv_core.IplImage imgs;
            Bitmap bitmap = null;
            FileOutputStream out = null;
            frameLength = g.getLengthInFrames();

            for (int i = 0; i<frameLength ; i++){
                //ImageIO.write(g.grab().getBufferedImage(), "png", new File("video-frame-" + System.currentTimeMillis() + ".png"));
                counter++;
                imgs = g.grab();
                bitmap.copyPixelsToBuffer(imgs.getByteBuffer());
                out = new FileOutputStream("i-"+ counter + ".png");
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.close();
            }

            g.stop();

		/*	Manipulate the Images
		Bitmap img = MediaStore.Images.Media.getBitmap();
		int w = img.getWidth()/2;
		int h = img.getHeight()/2;
		int rgb = img.getPixel(w, h);
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >> 8) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		System.out.println("RGB: " + rgb);
		System.out.println("Red: " + red);
		System.out.println("Green: " + green);
		System.out.println("Blue: " + blue);*/

    }

}
