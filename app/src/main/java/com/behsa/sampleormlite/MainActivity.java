package com.behsa.sampleormlite;

import java.sql.SQLException;


import com.behsa.sampleormlite.database.DatabaseHelper;
import com.behsa.sampleormlite.database.sample;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    DatabaseHelper databaseHelper = null;
    EditText et ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et = (EditText) findViewById(R.id.editText1);
        Button b = (Button) findViewById(R.id.button1);

        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sample sm = new sample();
                sm.Name = et.getText().toString();
                Dao<sample, Integer> techerDao;
                try {
                    techerDao = getHelper().getsampleDao();
                    //This is the way to insert data into a database table
                    techerDao.create(sm);
                    reset();
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(MainActivity.this, "Error in insert", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }
    private void reset()
    {
        et.setText("");
    }
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(MainActivity.this,DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

		/*
		 * You'll need this in your class to release the helper when done.
		 */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
