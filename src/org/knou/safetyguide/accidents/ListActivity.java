package org.knou.safetyguide.accidents;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.knou.safetyguide.R;
import org.knou.safetyguide.model.Weather;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */

public class ListActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_list);

        final ListView weatherEntryListView = (ListView) findViewById(R.id.listView1);
        final WeatherAdapter weatherEntryAdapter = new WeatherAdapter(this, R.layout.weather_entry_list_item);
        weatherEntryListView.setAdapter(weatherEntryAdapter);
        weatherEntryListView.setOnItemClickListener(mItemClickListener);
        DataBaseHelper dbhelper = new DataBaseHelper(getApplicationContext());

        Intent intent = getIntent();
        int extraCode = intent.getIntExtra("extraCode", 0);
        String extraName = null;

        switch(extraCode) {
	        case R.id.btnAcc1: extraName = "건축물"; break;
	        case R.id.btnAcc2: extraName = "시설물"; break;
	        case R.id.btnAcc3: extraName = "도로"; break;
	        case R.id.btnAcc4: extraName = "댐"; break;
	        case R.id.btnAcc5: extraName = "철도"; break;
	        case R.id.btnAcc6: extraName = "상하수도"; break;
        }

        if(extraName != null) {
            for (final Weather entry : dbhelper.getWeathersByClass1(extraName)) {
                weatherEntryAdapter.add(entry);
            }
        }
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            Weather tv = (Weather)parent.getAdapter().getItem(position);

            Intent newIntent = new Intent(ListActivity.this, ViewActivity.class);
            newIntent.putExtra("code", tv.getCode());
            startActivity(newIntent);
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
