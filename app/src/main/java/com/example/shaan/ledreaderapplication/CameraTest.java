
package com.example.shaan.ledreaderapplication;

import android.graphics.Bitmap;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacpp.opencv_core;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CameraTest {
	private static int counter = 0;
	private static int frameLength = 0;
	public static void main (String[] args) throws Exception, IOException {

        FFmpegFrameGrabber g = new FFmpegFrameGrabber("LEDAnalysis.mp4");
        g.start();


        Bitmap bitmap = null;
        FileOutputStream out = null;
        frameLength = g.getLengthInFrames();

        for (int i = 0; i<frameLength ; i++){
            //ImageIO.write(g.grab().getBufferedImage(), "png", new File("video-frame-" + System.currentTimeMillis() + ".png"));
            counter++;
            opencv_core.IplImage imgs = g.grab();
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
