package com.icelife.cmcontactsmanager.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by ICELIFE on 19/11/14.
 */
public class CMDatabaseManager {
    public static ArrayList<CMContactModel> getAllRecords(SQLiteDatabase database) {
        String executeComand = "SELECT * FROM " + CMDatabase.CONTACTS_TABLE_NAME;
        Cursor cursor = database.rawQuery(executeComand, null);
        ArrayList<CMContactModel> resultModels = new ArrayList<CMContactModel>();
        while (cursor.moveToFirst()) {
            CMContactModel tempModel = new CMContactModel();
            tempModel.setID(cursor.getInt(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_UID)));
            tempModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_FIRST_NAME)));
            tempModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_LAST_NAME)));
            tempModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_PHONE_NUMBER)));
            resultModels.add(tempModel);
        }
        return resultModels;
    }

    public static CMContactModel getItemByID(SQLiteDatabase database, long id) {
        String executeComand = "SELECT " + id + " FROM " + CMDatabase.CONTACTS_TABLE_NAME;
        Cursor cursor = database.rawQuery(executeComand, null);
        CMContactModel resultModel = new CMContactModel();
        while (cursor.moveToFirst()) {
            resultModel.setID(cursor.getInt(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_UID)));
            resultModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_FIRST_NAME)));
            resultModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_LAST_NAME)));
            resultModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_PHONE_NUMBER)));
        }
        return resultModel;
    }

    public static void saveModel(SQLiteDatabase database, CMContactModel model) {
        if (model.getID() != -1) {
            CMContactModel tempModel = CMDatabaseManager.getItemByID(database, model.getID());
            if (tempModel.getID() == -1) {
                CMDatabaseManager.insertModel(database, model);
            } else {
                CMDatabaseManager.updateModel(database, model);
            }
        } else {
            CMDatabaseManager.updateModel(database, model);
        }
    }

    private static void updateModel(SQLiteDatabase database, CMContactModel model) {
        String executeComand =      "UPDATE " +
                CMDatabase.CONTACTS_TABLE_NAME +
                " SET " + CMDatabase.CONTACTS_TABLE_FIRST_NAME + "="
                + model.getFirstName() + ","
                + CMDatabase.CONTACTS_TABLE_LAST_NAME + "="
                + model.getLastName() + ","
                + CMDatabase.CONTACTS_TABLE_PHONE_NUMBER + "="
                + model.getPhoneNumber() + "')"
                + ") VALUES ('"
                + model.getFirstName() + "'"
                + "'" + model.getLastName() + "'"
                + "'" + model.getPhoneNumber() + "')";
        database.execSQL(executeComand);
    }

    private static void insertModel(SQLiteDatabase database, CMContactModel model) {
        String executeComand =      "INSERT INTO " +
                CMDatabase.CONTACTS_TABLE_NAME +
                " (" + CMDatabase.CONTACTS_TABLE_FIRST_NAME + ","
                + CMDatabase.CONTACTS_TABLE_LAST_NAME + ","
                + CMDatabase.CONTACTS_TABLE_PHONE_NUMBER
                + ") VALUES ('"
                + model.getFirstName() + "'"
                + "'" + model.getLastName() + "'"
                + "'" + model.getPhoneNumber() + "')";
        database.execSQL(executeComand);
    }
}
