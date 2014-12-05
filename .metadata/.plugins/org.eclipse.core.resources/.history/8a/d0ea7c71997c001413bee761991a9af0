package com.icelife.cmcontactsmanager;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.icelife.cmcontactsmanager.Database.CMContactModel;
import com.icelife.cmcontactsmanager.Database.CMDatabase;
import com.icelife.cmcontactsmanager.Database.CMDatabaseManager;

/**
 * Created by ICELIFE on 18/11/14.
 */
public class CMItemActivity extends Activity {

    private CMContactModel modelForDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        long itemID = getIntent().getExtras().getLong("id", -1);
        if (itemID != -1) {
            CMDatabase contactDatabase = new CMDatabase(this);
            SQLiteDatabase database = contactDatabase.getWritableDatabase();
            this.modelForDisplay = CMDatabaseManager.getItemByID(database, itemID);
            database.close();
            contactDatabase.close();
            EditText firstName = (EditText)findViewById(R.id.textViewFirstName);
            firstName.setText(this.modelForDisplay.getFirstName());
            EditText lastName = (EditText)findViewById(R.id.textViewLastName);
            lastName.setText(this.modelForDisplay.getLastName());
        } else {
            this.modelForDisplay = new CMContactModel();
            EditText firstName = (EditText)findViewById(R.id.textViewFirstName);
            firstName.setText("");
            EditText lastName = (EditText)findViewById(R.id.textViewLastName);
            lastName.setText("");
        }
    }


    public void itemSaveButtonOnClickAction(View view) {
        EditText firstName = (EditText)findViewById(R.id.textViewFirstName);
        this.modelForDisplay.setFirstName(firstName.getText().toString());
        EditText lastName = (EditText)findViewById(R.id.textViewLastName);
        this.modelForDisplay.setLastName(lastName.getText().toString());
        CMDatabase contactDatabase = new CMDatabase(this);
        SQLiteDatabase database = contactDatabase.getWritableDatabase();
        CMDatabaseManager.saveModel(database, this.modelForDisplay);
        database.close();
        contactDatabase.close();
    }
}
