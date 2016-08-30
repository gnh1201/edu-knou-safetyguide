package org.knou.safetyguide.accidents;

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

import org.knou.safetyguide.R;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */

public class MainAccActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_main);

        final Button btnAcc1 = (Button)findViewById(R.id.btnAcc1);
        final Button btnAcc2 = (Button)findViewById(R.id.btnAcc2);
        final Button btnAcc3 = (Button)findViewById(R.id.btnAcc3);
        final Button btnAcc4 = (Button)findViewById(R.id.btnAcc4);
        final Button btnAcc5 = (Button)findViewById(R.id.btnAcc5);
        final Button btnAcc6 = (Button)findViewById(R.id.btnAcc6);

        btnAcc1.setOnClickListener(mClickListener);
        btnAcc2.setOnClickListener(mClickListener);
        btnAcc3.setOnClickListener(mClickListener);
        btnAcc4.setOnClickListener(mClickListener);
        btnAcc5.setOnClickListener(mClickListener);
        btnAcc6.setOnClickListener(mClickListener);

        DBChecker chkDB = new DBChecker(getApplicationContext(), "accidents2.db");
        try {
            boolean bResult = chkDB.isCheckDB();
            Log.d("Accidents Dict", "DB Check=" + bResult);
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
                case R.id.btnAcc1: extraCode = "btnAcc1"; break;
                case R.id.btnAcc2: extraCode = "btnAcc2"; break;
                case R.id.btnAcc3: extraCode = "btnAcc3"; break;
                case R.id.btnAcc4: extraCode = "btnAcc4"; break;
                case R.id.btnAcc5: extraCode = "btnAcc5"; break;
                case R.id.btnAcc6: extraCode = "btnAcc6"; break;
            }

            Intent readyIntent = new Intent(MainAccActivity.this, targetActivity);
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
