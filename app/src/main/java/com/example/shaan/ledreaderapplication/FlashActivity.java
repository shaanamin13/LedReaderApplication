package com.example.shaan.ledreaderapplication;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FlashActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        Button breakerOne = (Button) findViewById(R.id.Breaker1);
        Button breakerTwo = (Button) findViewById(R.id.Breaker2);
        Button breakerThree = (Button) findViewById(R.id.Breaker3);
        Button breakerFour = (Button) findViewById(R.id.Breaker4);
        Button breakerFive = (Button) findViewById(R.id.Breaker5);
        Button breakerSix = (Button) findViewById(R.id.Breaker6);

        breakerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Camera cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();

                cam.stopPreview();
                cam.release();
                flashFunction(500);


            }
        });


        breakerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Camera cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();

                cam.stopPreview();
                cam.release();

                flashFunction(1000);

                startCamera();
            }
        });

        breakerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Camera cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();

                    cam.stopPreview();
                    cam.release();

                    flashFunction(1500);



            }
        });

        breakerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Camera cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();

                    cam.stopPreview();
                    cam.release();

                    flashFunction(2000);

            }
        });

        breakerFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Camera cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();

                    cam.stopPreview();
                    cam.release();

                    flashFunction(2500);


            }
        });

        breakerSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Camera cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();

                    cam.stopPreview();
                    cam.release();

                    flashFunction(3000);



            }
        });

    }

    private void flashFunction(final int delay) {
        Runnable runnable = new Runnable() {
            public void run() {

                Camera cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cam.stopPreview();
                cam.release();


                startCamera();
            }
        };
        new Thread(runnable).start();
    }


    public void startCamera() {
        Intent intent = new Intent(this, CameraCommActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash, menu);
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
}
