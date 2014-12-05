package com.icelife.cmcontactsmanager.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by ICELIFE on 19/11/14.
 */
public class CMDatabaseManager {
    public static CMContactModel[] getAllRecords(SQLiteDatabase database) {
        String executeComand = "SELECT * FROM " + CMDatabase.CONTACTS_TABLE_NAME;
        Cursor cursor = database.rawQuery(executeComand, null);
        ArrayList<CMContactModel> resultModels = new ArrayList<CMContactModel>();
        cursor.moveToFirst();
        do {
        	CMContactModel tempModel = new CMContactModel();
            tempModel.setID(cursor.getInt(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_UID)));
            tempModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_FIRST_NAME)));
            tempModel.setLastName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_LAST_NAME)));
            tempModel.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_PHONE_NUMBER)));
            resultModels.add(tempModel);
        } while (cursor.moveToNext());
        CMContactModel[] models = resultModels.toArray(new CMContactModel[resultModels.size()]);
        return models;
    }

    public static CMContactModel getItemByID(SQLiteDatabase database, long id) {
    	String executeComand = "SELECT * FROM " + CMDatabase.CONTACTS_TABLE_NAME + " WHERE " + CMDatabase.CONTACTS_TABLE_UID + " = " + id;
        Cursor cursor = database.rawQuery(executeComand, null);
        CMContactModel resultModel = new CMContactModel();
        if (cursor.moveToFirst()) {
        	resultModel.setID(cursor.getInt(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_UID)));
            resultModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_FIRST_NAME)));
            resultModel.setLastName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_LAST_NAME)));
            resultModel.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_PHONE_NUMBER)));
        }
        return resultModel;
    }

    public static CMContactModel[] getModelsByModel(SQLiteDatabase database, CMContactModel model) {
    	String executeComand = "SELECT * FROM " + CMDatabase.CONTACTS_TABLE_NAME
    							+ " WHERE " + CMDatabase.CONTACTS_TABLE_FIRST_NAME + " = '" + model.getFirstName() + "'"
    							+ " AND " + CMDatabase.CONTACTS_TABLE_LAST_NAME + " = '" + model.getLastName() + "'"
    							+ " AND " + CMDatabase.CONTACTS_TABLE_PHONE_NUMBER + " = '" + model.getPhoneNumber() + "'";
        Cursor cursor = database.rawQuery(executeComand, null);
        ArrayList<CMContactModel> resultModels = new ArrayList<CMContactModel>();
        cursor.moveToFirst();
        do {
        	CMContactModel tempModel = new CMContactModel();
            tempModel.setID(cursor.getInt(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_UID)));
            tempModel.setFirstName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_FIRST_NAME)));
            tempModel.setLastName(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_LAST_NAME)));
            tempModel.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CMDatabase.CONTACTS_TABLE_PHONE_NUMBER)));
            resultModels.add(tempModel);
        } while (cursor.moveToNext());
        CMContactModel[] models = resultModels.toArray(new CMContactModel[resultModels.size()]);
        return models;
    }
    
    public static CMContactModel saveModel(SQLiteDatabase database, CMContactModel model) {
    	if (model.getID() != -1) {
            CMContactModel tempModel = CMDatabaseManager.getItemByID(database, model.getID());
            if (tempModel.getID() == -1) {
            	return CMDatabaseManager.insertModel(database, model);
            } else {
            	return CMDatabaseManager.updateModel(database, model);
            }
        } else {
            return CMDatabaseManager.insertModel(database, model);
        }
    }

    private static CMContactModel updateModel(SQLiteDatabase database, CMContactModel model) {
        String executeComand = "UPDATE " + CMDatabase.CONTACTS_TABLE_NAME + " SET "
                + CMDatabase.CONTACTS_TABLE_FIRST_NAME + "='" + model.getFirstName() + "',"
                + CMDatabase.CONTACTS_TABLE_LAST_NAME + "='" + model.getLastName() + "',"
                + CMDatabase.CONTACTS_TABLE_PHONE_NUMBER + "='" + model.getPhoneNumber() + "'"
                + "WHERE " + CMDatabase.CONTACTS_TABLE_UID + "='" + model.getID() + "';";
        database.execSQL(executeComand);
        CMContactModel[] models = CMDatabaseManager.getModelsByModel(database, model);
        return models[models.length - 1];
    }

    private static CMContactModel insertModel(SQLiteDatabase database, CMContactModel model) {
        String executeComand = "INSERT INTO " + CMDatabase.CONTACTS_TABLE_NAME
        		+ " (" + CMDatabase.CONTACTS_TABLE_FIRST_NAME + "," + CMDatabase.CONTACTS_TABLE_LAST_NAME + "," + CMDatabase.CONTACTS_TABLE_PHONE_NUMBER
                + ") VALUES ('" + model.getFirstName() + "','" + model.getLastName() + "','" + model.getPhoneNumber() + "')";
        database.execSQL(executeComand);
        CMContactModel[] models = CMDatabaseManager.getModelsByModel(database, model);
        return models[models.length - 1];
    }
}
