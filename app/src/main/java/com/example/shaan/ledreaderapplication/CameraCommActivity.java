package com.example.shaan.ledreaderapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraCommActivity extends Activity implements SurfaceHolder.Callback {
    private MediaRecorder recorder;
    private SurfaceHolder surfaceHolder;
    private CamcorderProfile camcorderProfile;
    private Camera camera;
    boolean recording = false;
    boolean usecamera = true;
    boolean previewRunning = false;
    SurfaceView surfaceView;
    Button btnStart, btnStop;
    File root;
    File file;
    Boolean isSDPresent;
    SimpleDateFormat simpleDateFormat;
    String timeStamp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        setContentView(R.layout.activity_camera_comm);

        initComs();
//        SurfaceView transparentView = (SurfaceView)findViewById(R.id.TransparentView);
//        SurfaceHolder holderTransparent = transparentView.getHolder();
//        holderTransparent.setFormat(PixelFormat.TRANSPARENT);
//        holderTransparent.addCallback(this);
//        holderTransparent.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        actionListener();
    }

    private void initComs() {
        simpleDateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
        timeStamp = simpleDateFormat.format(new Date());
        camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        btnStop = (Button) findViewById(R.id.btn_stop);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }




    private void actionListener() {
        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    prepareRecorder();
                    recorder.start();
                    Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_SHORT).show();
                    recording = true;
                    btnStop.setVisibility(View.INVISIBLE);

                new CountDownTimer(5000, 1000) {
                    public void onFinish() {
                        recording=false;
                        recorder.stop();
                        recorder.reset();
                        recorder.release();
                        Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();
                        viewResults();

                    }

                    public void onTick(long millisUntilFinished) {

                    }
                }.start();



            }
        });
    }

    private void prepareRecorder() {
        recorder = new MediaRecorder();

        recorder.setOrientationHint(90);
        recorder.setPreviewDisplay(surfaceHolder.getSurface());
        if (usecamera) {

            camera.unlock();
            recorder.setCamera(camera);
        }
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);

        recorder.setProfile(camcorderProfile);

        File ledDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/led/");
        ledDirectory.mkdirs();
        if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.MPEG_4) {
            recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/led/LEDAnalysis.mp4");
        } else if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.MPEG_4) {
            recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/led/LEDAnalysis.mp4");
        } else {
            recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/led/LEDAnalysis.mp4");
        }

        try {
            recorder.prepare();

        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }

    }

    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("onsurfacecreated");

        if (usecamera) {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

            camera.setDisplayOrientation(90);


            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
                camera.autoFocus(null);
                previewRunning = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        camera.release();
        if (usecamera) {
            previewRunning = false;
            // camera.lock();

            camera.release();
        }
        finish();
    }


    public void viewResults(){
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

}
