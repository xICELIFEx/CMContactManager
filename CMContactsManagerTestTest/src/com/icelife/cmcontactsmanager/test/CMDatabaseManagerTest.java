package com.icelife.cmcontactsmanager.test;

import com.icelife.cmcontactsmanager.Database.CMContactModel;
import com.icelife.cmcontactsmanager.Database.CMDatabase;
import com.icelife.cmcontactsmanager.Database.CMDatabaseManager;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class CMDatabaseManagerTest extends AndroidTestCase {
	
	private SQLiteDatabase databaseSQLForTest;
	private CMDatabase databaseForTest;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		this.databaseForTest = new CMDatabase(getContext());
        this.databaseSQLForTest = this.databaseForTest.getWritableDatabase();
		
        this.databaseSQLForTest.execSQL(CMDatabase.SQL_DELETE_DATABASE );
        this.databaseSQLForTest.execSQL(CMDatabase.SQL_CREATE_DATABASE);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (this.databaseForTest != null) {
			this.databaseForTest.close();
	        this.databaseSQLForTest.close();
		}
	}
	
	//tests
	
	public void testUpdateIDValueInModel() {
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_DELETE_DATABASE);
        this.databaseSQLForTest.execSQL(CMDatabase.SQL_CREATE_DATABASE);
        
        CMContactModel actualModel = new CMContactModel();
        assertEquals("id value is not -1", actualModel.getID(), -1);
        actualModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModel);
		assertTrue("id value is -1", actualModel.getID() != -1);
	}
	
	public void testReadAllRecords() {
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_DELETE_DATABASE);
        this.databaseSQLForTest.execSQL(CMDatabase.SQL_CREATE_DATABASE);
		
		CMContactModel firstModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, new CMContactModel("one", "two", "90-6543-32212"));
		CMContactModel secondModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, new CMContactModel("many words in name", "many words in last name", "90(6543)32212(234567890-98765432)"));
		CMContactModel thirdModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, new CMContactModel("qrtyuiopogfdsdfghjvcxzxcvbnjfdscvjfhejfvgwdejfwejhcbvweuyfgwueyfbwejhbvweuhjfwegyagejjhafuygjhgwfuygw", "jdhefhshgvwfhfaehjhegefyw3ydeahgkywqdglhgrefkuywgdfkhesgfhgsvkuagfkjdghawkygrfhvkszjdfgjhkagfkjfgh", "gr45y345554626yt23rtgw34rtws4rtfwetf90(6543)32212(234567890-98765432)"));
		CMContactModel fourModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, new CMContactModel("many words in name with space but very long fjsagh hjgf jhgehj gh gsjh gwehr gwehjgr fjhsdgfjh gfk dpweulrf hclvkfzh jhsdf lkjhlkfjh lksjdfh lkjsdhflk jhsfk jhsdkjfh kghvkwgrf ", "many words in last name with space but very long afghjhgfzjkdfkjhfd hjgj hfjhfhdb ", "90(6543)32212(234567890-98765432)"));
		CMContactModel[] arrayForTest = {firstModel, secondModel, thirdModel, fourModel};
        
        CMContactModel[] readedList = CMDatabaseManager.getAllRecords(this.databaseSQLForTest);
        for (CMContactModel model : readedList) {
        	this.checkModel(model, arrayForTest);
        }
	}
	
	public void testInsertModel() {
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_DELETE_DATABASE);
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_CREATE_DATABASE);
		CMContactModel actualModel = new CMContactModel("Vasil", "Veselcov", "not exist");
		actualModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModel);
		 
		CMContactModel expectedModel = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModel.getID());
		this.checkModel(expectedModel, actualModel);
	}
	 
	public void testUpdateModel() {
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_DELETE_DATABASE);
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_CREATE_DATABASE);
		CMContactModel actualModel = new CMContactModel("Vasil", "Veselcov", "not exist");
		actualModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModel);
		
		actualModel.setFirstName("update Vasa");
		actualModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModel);
		CMContactModel expectedFirstNameModel = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModel.getID()); 
		this.checkModel(expectedFirstNameModel, actualModel);
		
		actualModel.setLastName("update Veselcov");
		actualModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModel);
		CMContactModel expectedLastNameModel = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModel.getID()); 
		this.checkModel(expectedLastNameModel, actualModel);
		
		actualModel.setPhoneNumber("update not exist");
		actualModel = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModel);
		CMContactModel expectedPhoneNumberModel = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModel.getID()); 
		this.checkModel(expectedPhoneNumberModel, actualModel);
	}
	
	public void testInsertModelWithEmptyData() {
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_DELETE_DATABASE);
		this.databaseSQLForTest.execSQL(CMDatabase.SQL_CREATE_DATABASE);
		CMContactModel actualModelWithEmptyFieelds = new CMContactModel();
		actualModelWithEmptyFieelds = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModelWithEmptyFieelds);
		CMContactModel expectedModelWithEmptyFields = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModelWithEmptyFieelds.getID()); 
		this.checkModel(expectedModelWithEmptyFields, actualModelWithEmptyFieelds);
		
		CMContactModel actualModelWithNotEmptyFirstName = new CMContactModel("vasa");
		actualModelWithNotEmptyFirstName = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModelWithNotEmptyFirstName);
		CMContactModel expectedModelWithNotEmptyFirstName = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModelWithNotEmptyFirstName.getID()); 
		this.checkModel(expectedModelWithNotEmptyFirstName, actualModelWithNotEmptyFirstName);
		
		CMContactModel actualModelWithNotEmptyLastName = new CMContactModel("", "veselcov");
		actualModelWithNotEmptyLastName = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModelWithNotEmptyLastName);
		CMContactModel expectedModelWithNotEmptyLastName = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModelWithNotEmptyLastName.getID()); 
		this.checkModel(expectedModelWithNotEmptyLastName, actualModelWithNotEmptyLastName);
		
		CMContactModel actualModelWithNotEmptyPhoneNumber = new CMContactModel("", "", "phone number");
		actualModelWithNotEmptyPhoneNumber = CMDatabaseManager.saveModel(this.databaseSQLForTest, actualModelWithNotEmptyPhoneNumber);
		CMContactModel expectedModelWithNotEmptyPhoneNumber = CMDatabaseManager.getItemByID(this.databaseSQLForTest, actualModelWithNotEmptyPhoneNumber.getID()); 
		this.checkModel(expectedModelWithNotEmptyPhoneNumber, actualModelWithNotEmptyPhoneNumber);
	}
	
	//helper functions
	
	private void checkModel(CMContactModel expectedModel, CMContactModel actualModel) {
		CMContactModel[] models = {actualModel};
		this.checkModel(expectedModel, models);
	}
	
	private void checkModel(CMContactModel model, CMContactModel[] models) {
		boolean isAlreadyFinded = false;
		for (CMContactModel modelFromModels : models) {
			if (modelFromModels.getID() == model.getID()) {
				if (!isAlreadyFinded) {
					isAlreadyFinded = true;
					assertEquals(" first names are not equals", model.getFirstName(), modelFromModels.getFirstName());
					assertEquals(" last names are not equals", model.getLastName(), modelFromModels.getLastName());
					assertEquals(" phones are not equals", model.getPhoneNumber(), modelFromModels.getPhoneNumber());
				} else {
					assertTrue("model already finded", false);
				}
			}
		}
		assertTrue("model not finded", isAlreadyFinded);
	}
	
}
