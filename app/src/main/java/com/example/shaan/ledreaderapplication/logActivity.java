package com.example.shaan.ledreaderapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.bytedeco.javacv.FrameGrabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class logActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);


        Button emailButton = (Button) findViewById(R.id.email_btn);
        getLogFile();

        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                doSendFile();

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log, menu);
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


    public void doSendFile() {
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/led/ledLogs.txt";
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"send@uiowa.edu"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Circuit Breaker Status Log");
        i.putExtra(Intent.EXTRA_TEXT, "Attached is the text file for the circuit breaker status log. \n Sent via Android Smartphone.");

        i.putExtra(Intent.EXTRA_STREAM, Uri.parse(fileName));

        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getBaseContext(),
                    "There are no email clients installed.", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public void getLogFile() {
        TableLayout logTable = (TableLayout) findViewById(R.id.logtble);


        try {
            Scanner logFile = new Scanner(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/led/ledLogs.txt"));

            while (logFile.hasNext()) {
                TableRow row = new TableRow(this);
                TextView tv = new TextView(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT);
                row.setLayoutParams(lp);



                String logText = logFile.nextLine();

                tv.setText(logText);
                row.addView(tv);
                logTable.addView(row);

                System.out.println(logText);

//                String attribute1 = BinarytoAscii.buildAttributes(1);
//                System.out.println(attribute1);
//                String attribute2 = BinarytoAscii.buildAttributes(2);
//                System.out.println(attribute2);
//                String attribute3 = BinarytoAscii.buildAttributes(3);
//                System.out.println(attribute3);
//                String attribute4 = BinarytoAscii.buildAttributes(4);
//                System.out.println(attribute4);
//                String attribute5 = BinarytoAscii.buildAttributes(5);
//                System.out.println(attribute5);
//                String attribute6 = BinarytoAscii.buildAttributes(6);
//                System.out.println(attribute6);
//                String attribute7 = BinarytoAscii.buildAttributes(7);
//                System.out.println(attribute7);
//                String attribute8 = BinarytoAscii.buildAttributes(8);
//                System.out.println(attribute8);
            }

            logFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



