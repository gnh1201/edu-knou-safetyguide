package org.knou.safetyguide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnWater = (Button)findViewById(R.id.btnWater);
        final Button btnDust = (Button)findViewById(R.id.btnDust);
        final Button btnSun = (Button)findViewById(R.id.btnSun);
        final Button btnSpark = (Button)findViewById(R.id.btnSpark);
        final Button btnUnknown = (Button)findViewById(R.id.btnUnknown);
        final Button btnInfo = (Button)findViewById(R.id.btnInfo);

        btnWater.setOnClickListener(mClickListener);
        btnDust.setOnClickListener(mClickListener);
        btnSun.setOnClickListener(mClickListener);
        btnSpark.setOnClickListener(mClickListener);
        btnUnknown.setOnClickListener(mClickListener);
        btnInfo.setOnClickListener(mClickListener);

        // DB �긽�깭 �솗�씤
        DBChecker chkDB = new DBChecker(getApplicationContext(), "weather2.db");
        try {
            boolean bResult = chkDB.isCheckDB();
            Log.d("WeatherDict", "DB Check=" + bResult);
            if(!bResult) {
                chkDB.copyDB();
            }
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "FAILED DB Creation!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

            Log.e("ErrorMessage : ", e.getMessage());
        }
    }

    final OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String extraCode = null;
            Class targetActivity = ListActivity.class;

            switch(v.getId()) {
                case R.id.btnWater: extraCode = "btnWater"; break;
                case R.id.btnDust: extraCode = "btnDust"; break;
                case R.id.btnSun: extraCode = "btnSun"; break;
                case R.id.btnSpark: extraCode = "btnSpark"; break;
                case R.id.btnUnknown: extraCode = "btnUnknown"; break;
                case R.id.btnInfo: targetActivity = InfoActivity.class; break;
            }

            Intent readyIntent = new Intent(MainActivity.this, targetActivity);
            if(extraCode != null) {
                readyIntent.putExtra("extraCode", v.getId());
            }
            startActivity(readyIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
