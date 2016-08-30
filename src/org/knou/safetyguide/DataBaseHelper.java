package org.knou.safetyguide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import org.knou.safetyguide.model.Weather;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University

 * Added Accidents data on 2016-08-11 by namhyeon
 *
 */


public class DataBaseHelper extends SQLiteOpenHelper
{
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device
    private static String DB_PATH = "";
    private static String DB_NAME ="weather2.db";// Database name
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DataBaseHelper(Context context)
    {
            super(context, DB_NAME, null, 1);// 1? its Database Version
            if(android.os.Build.VERSION.SDK_INT >= 17){
                DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            }
            else
            {
                DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            }
            this.mContext = context;
        }

    public void createDataBase() throws IOException
    {
        //If database not exists copy it from the assets

        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close()
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }
    // Get Weather Data(Single)
    public Weather getWeather(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from weather where code = '" + code + "'", null);

        if (cursor != null)
            cursor.moveToFirst();

        Weather weather = new Weather();
        weather.setClass1(cursor.getString(0));
        weather.setClass2(cursor.getString(1));
        weather.setClass3(cursor.getString(2));
        weather.setCode(cursor.getString(3));
        weather.setName(cursor.getString(4));
        weather.setType(cursor.getString(5));
        weather.setDesc(cursor.getString(6));
        weather.setSample(cursor.getString(7));
        weather.setCopyright(cursor.getString(8));
        weather.setUrl(cursor.getString(9));

        return weather;
    }

    // Get Weather Data(Multiple)
    public ArrayList<Weather> getWeathersByClass1(String class1) {
        ArrayList<Weather> weathers = new ArrayList<Weather>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from weather where class1 = '" + class1 + "'", null);

        Weather weather = null;
        if (cursor.moveToFirst()) {
            do {
                weather = new Weather();
                weather.setClass1(cursor.getString(0));
                weather.setClass2(cursor.getString(1));
                weather.setClass3(cursor.getString(2));
                weather.setCode(cursor.getString(3));
                weather.setName(cursor.getString(4));
                weather.setType(cursor.getString(5));
                weather.setDesc(cursor.getString(6));
                weather.setSample(cursor.getString(7));
                weather.setCopyright(cursor.getString(8));
                weather.setUrl(cursor.getString(9));

                weathers.add(weather);
            } while (cursor.moveToNext());
        }

        return weathers;
    }
}