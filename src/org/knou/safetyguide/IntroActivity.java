package org.knou.safetyguide;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.knou.safetyguide.accidents.MainAccActivity;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */

public class IntroActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        final Button btnIntro1 = (Button)findViewById(R.id.btnIntro1);
        final Button btnIntro2 = (Button)findViewById(R.id.btnIntro2);

        btnIntro1.setOnClickListener(mClickListener);
        btnIntro2.setOnClickListener(mClickListener);
    }
    
    final OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String extraCode = null;
            Class<?> targetActivity = MainActivity.class;

            switch(v.getId()) {
                case R.id.btnIntro1:
                	extraCode = "btnIntro1"; break;
                case R.id.btnIntro2:
                	extraCode = "btnIntro2";
                	targetActivity = MainAccActivity.class;
                	break;
            }

            Intent readyIntent = new Intent(IntroActivity.this, targetActivity);
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
