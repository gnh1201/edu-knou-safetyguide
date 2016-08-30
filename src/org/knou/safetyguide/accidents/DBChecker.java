package org.knou.safetyguide.accidents;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */

class DBChecker {
    private Context mContext = null;
    private String PACKAGE_NAME;
    private String DB_NAME;

    public DBChecker(Context context, String dbname) {
        mContext = context;
        PACKAGE_NAME = context.getPackageName();
        DB_NAME = dbname;
    }

    public boolean isCheckDB(){
        String filePath = "/data/data/" + PACKAGE_NAME + "/databases/" + DB_NAME;
        File file = new File(filePath);

        if(file.exists()) {
            return true;
        }

        return false;

    }
    public void copyDB(){
        Log.d("WeatherDict", "copyDB");
        AssetManager manager = mContext.getAssets();
        String folderPath = "/data/data/" + PACKAGE_NAME + "/databases";
        String filePath = "/data/data/" + PACKAGE_NAME + "/databases/" + DB_NAME;
        File folder = new File(folderPath);
        File file = new File(filePath);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            InputStream is = manager.open("db/" + DB_NAME);
            BufferedInputStream bis = new BufferedInputStream(is);

            if (folder.exists()) {
            } else {
                folder.mkdirs();
            }
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }

            bos.flush();

            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("ErrorMessage : ", e.getMessage());
        }
    }
}
