package com.icelife.cmcontactsmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.icelife.cmcontactsmanager.Database.CMContactModel;
import com.icelife.cmcontactsmanager.Database.CMDatabase;
import com.icelife.cmcontactsmanager.Database.CMDatabaseManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CMContactModel modelFirst = new CMContactModel("first");
//        CMContactModel modelSecond = new CMContactModel("Second");
//        CMContactModel modelThrird = new CMContactModel("Thrird");

        CMDatabase cmDatabase = new CMDatabase(this);
        SQLiteDatabase database = cmDatabase.getWritableDatabase();
//        CMDatabaseManager.saveModel(database, modelFirst);
//        CMDatabaseManager.saveModel(database, modelSecond);
//        CMDatabaseManager.saveModel(database, modelThrird);
        
        CMArrayAdapter arrayAdapter = new CMArrayAdapter(this, CMDatabaseManager.getAllRecords(database));

        ListView listViewContacts = (ListView)findViewById(R.id.listViewForContacts);
        listViewContacts.setAdapter(arrayAdapter);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CMContactModel tempModel = (CMContactModel)adapterView.getItemAtPosition(i);
                MainActivity.this.intentToItemActivityWithContactId(tempModel.getID());
            }
        };

        listViewContacts.setOnItemClickListener(onItemClickListener);
        database.close();
        cmDatabase.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    public void addButtunOnClickAction(View view) {
        this.intentToItemActivityWithContactId(-1);
    }

    public void intentToItemActivityWithContactId(long id) {
        Intent intent = new Intent(MainActivity.this, CMItemActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
