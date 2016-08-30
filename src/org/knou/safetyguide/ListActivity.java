package org.knou.safetyguide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
        setContentView(R.layout.activity_list);

        final ListView weatherEntryListView = (ListView) findViewById(R.id.listView1);
        final WeatherAdapter weatherEntryAdapter = new WeatherAdapter(this, R.layout.weather_entry_list_item);
        weatherEntryListView.setAdapter(weatherEntryAdapter);
        weatherEntryListView.setOnItemClickListener(mItemClickListener);
        DataBaseHelper dbhelper = new DataBaseHelper(getApplicationContext());

        Intent intent = getIntent();
        int extraCode = intent.getIntExtra("extraCode", 0);
        String extraName = null;

        switch(extraCode) {
            case R.id.btnWater: extraName = "물현상"; break;
            case R.id.btnDust: extraName = "먼지현상"; break;
            case R.id.btnSun: extraName = "빛현상"; break;
            case R.id.btnSpark: extraName = "전기현상"; break;
            case R.id.btnUnknown: extraName = "기타현상"; break;
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
