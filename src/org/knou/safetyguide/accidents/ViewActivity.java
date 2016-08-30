package org.knou.safetyguide.accidents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.knou.safetyguide.R;
import org.knou.safetyguide.model.Weather;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */


public class ViewActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_view);
        
        // Get Weather Image
        Intent intent = getIntent();
        String imageCode = intent.getStringExtra("code");
        DataBaseHelper dbhelper = new DataBaseHelper(getApplicationContext());
        Weather viewData = dbhelper.getWeather(imageCode);

        if(viewData.getCode() != null) {
            // Change Title and Description
            final TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
            final TextView txtDesc = (TextView)findViewById(R.id.txtDesc);

            txtTitle.setText((viewData.getName() == null) ? "title" : viewData.getName());
            txtDesc.setText((viewData.getDesc() == null) ? "description" : viewData.getDesc());
            txtDesc.setMovementMethod(new ScrollingMovementMethod());
        } else {
            finishActivity(107);
        }
    }

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
