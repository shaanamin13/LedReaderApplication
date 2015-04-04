package com.example.shaan.ledreaderapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
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
                TextView tv0 = new TextView(this);
                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                TextView tv3 = new TextView(this);
                TextView tv4 = new TextView(this);
                TextView tv5 = new TextView(this);
                TextView tv6 = new TextView(this);
                TextView tv7 = new TextView(this);
                TextView tv8 = new TextView(this);
                TextView tv9 = new TextView(this);


                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(25,0,0,0);
                row.setLayoutParams(lp);


                String logText = logFile.nextLine();
                String[] split = logText.split("\\s+");
                if (split.length > 1) {
                    if (!split[1].matches("\\d+")) {
                        String errorStr = "";
                        tv0.setText(split[0]);
                        row.addView(tv0);

                        for (int i = 1; i < split.length; i++) {
                            errorStr = errorStr + " " + split[i];
                        }
                        tv1.setText(errorStr);


                        row.addView(tv1);

                    } else {

                        for (int i = 0; i < split.length; i++) {
                            if (i == 0) {
                                tv0.setText(split[i]);
                                row.addView(tv0);
                                tv1.setText("-");

                                row.addView(tv1);


                            } else if (i == 1) {
                                tv2.setText(split[i]);
                                tv2.setBackgroundColor(846033);
                                row.addView(tv2);
                            } else if (i == 2) {
                                tv3.setText(split[i]);
                                                               
                                row.addView(tv3);
                            } else if (i == 3) {
                                tv4.setText(split[i]);
                                row.addView(tv4);
                            } else if (i == 4) {
                                tv5.setText(split[i]);
                                row.addView(tv5);

                            } else if (i == 5) {
                                tv6.setText(split[i]);
                                row.addView(tv6);
                                tv7.setText("-");
                                row.addView(tv7);
                                tv8.setText("1");
                                row.addView(tv8);
                                tv9.setText("0");
                                row.addView(tv9);
                            }

                        }
                    }
                } else {
                    System.out.println("Error");
                }

                logTable.addView(row);
                System.out.println("Log Text: " + logText);
            }

            logFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



