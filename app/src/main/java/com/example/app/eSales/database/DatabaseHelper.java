/*package com.example.app.eSales.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.app.eSales.AR_Customer;

import com.example.app.eSales.AR_SALESPERSON;
import com.example.app.eSales.CUSTOM_CUSTID_SlsperID;
import com.example.app.eSales.CUSTOM_INVENTORY;
import com.example.app.eSales.OM_SALESORD;
//import com.example.app.eSales.data.AR_SALESPERSON;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "eSales1.db3";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    //    Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
        this.DB_PATH = this.context.getDatabasePath(this.DB_NAME).getAbsolutePath();

    }

    //    Gọi khi database được truy cập, nhưng chưa được tạo xong
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    //    Gọi ra phiên bản database được nâng cấp
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            try {
                copy_Database();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //    Tạo database
    public void create_Database() throws IOException {
        if (!check_Database()){

            this.getReadableDatabase();
            this.close();

            try{
                copy_Database();
                Log.d("create_Database", "create_Database created");
            }catch (IOException e){
                throw new Error("Error copy database");
            }
        }
    }

    //    Kiểm tra database có tồn tại
    public boolean check_Database(){

        File file = new File(DB_PATH);

        return file.exists();
    }

    //    Copy database từ Asset
    public void copy_Database() throws IOException {

        InputStream inputStream = this.context.getAssets().open(DB_NAME);
        OutputStream outputStream = new FileOutputStream(DB_PATH);

        byte[] mBuffer = new byte[1024];
        int length;

        while ((length = inputStream.read(mBuffer)) > 0){

            outputStream.write(mBuffer, 0, length);

        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }


    //    Phương thức mở Database
    public void open_Database(){

        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            return;
        }

        sqLiteDatabase = SQLiteDatabase.openDatabase(context.getDatabasePath(DB_NAME).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Log.d("open_database", "Database opened");

    }

    @Override
    public synchronized void close() {

        if (sqLiteDatabase != null){

            sqLiteDatabase.close();
        }

        super.close();
    }

    //    Phương thức đóng Database
//    public void close_Database(){
//        if (sqLiteDatabase != null){
//            sqLiteDatabase.close();
//            Log.d("close_Database", "Database closed");
//        }
//    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new SQLiteException("Can't downgrade database from version " +
                oldVersion + " to " + newVersion);
    }

    //    Phương thức lấy danh sách bảng AR_Customer
    public ArrayList<AR_Customer> getList_AR_Customer(AR_Customer ar_customer, ArrayList<AR_Customer> ar_customers){
        ar_customer = null;
        ar_customers = new ArrayList<>();
        open_Database();
       // sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from AR_CUSTOMER", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ar_customer = new AR_Customer(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            ar_customers.add(ar_customer);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();
        Log.d("getList_AR_Customer", "getList_AR_Customer activated");

        return ar_customers;
    }

    //    Phương thức lấy danh sách bảng AR_SALESPERSON
    public ArrayList<AR_SALESPERSON> getList_AR_SALESPERSON(AR_SALESPERSON ar_salesperson, ArrayList<AR_SALESPERSON> ar_salespeople){
        ar_salesperson = null;
        ar_salespeople =  new ArrayList<>();
        open_Database();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from AR_SALESPERSON", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ar_salesperson = new AR_SALESPERSON(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            ar_salespeople.add(ar_salesperson);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();

        Log.d("getList_AR_SALESPERSON", "getList_AR_SALESPERSON activated");

        return ar_salespeople;
    }
//    Phương thức lấy danh sách đơn hàng
    public ArrayList<OM_SALESORD> getList_OM_SALESORD(OM_SALESORD om_salesord, ArrayList<OM_SALESORD> om_salesords, String CustID, String SlsperID){
        om_salesord = null;
        om_salesords = new ArrayList<>();
        open_Database();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from OM_SALESORD where CustID = ? and SlsperID = ?", new String[] {CustID, SlsperID});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            om_salesord = new OM_SALESORD(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getString(5),
                    cursor.getString(6));

            om_salesords.add(om_salesord);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();

        return om_salesords;
    }

//    Phương thức lấy danh sách đơn hàng sau khi lọc
    public ArrayList<OM_SALESORD> get_FilterList_OM_SALESORD(OM_SALESORD om_salesord, ArrayList<OM_SALESORD> om_salesords, String s, String CustID, String SlsperID){

        om_salesord = null;
        om_salesords = new ArrayList<>();
        open_Database();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from OM_SALESORD where OrderDate = ? and CustID = ? and SlsperID = ?", new String[] {s, CustID, SlsperID});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            om_salesord = new OM_SALESORD(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );

            om_salesords.add(om_salesord);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();

        return om_salesords;
    }

//    Phương thức lấy danh sách mặt hàng trong kho
    public ArrayList<CUSTOM_INVENTORY> getList_AR_INVENTORY(CUSTOM_INVENTORY custom_inventory, ArrayList<CUSTOM_INVENTORY> custom_inventories){

        custom_inventory = null;
        custom_inventories = new ArrayList<>();
        open_Database();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from IN_INVENTORY", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            custom_inventory = new CUSTOM_INVENTORY(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    "0",
                    "0"
            );

            custom_inventories.add(custom_inventory);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();

        return custom_inventories;
    }

//    Phương thức insert data vào bảng OM_SALESORDDET
    public void insert_OM_SALESORDET(String OderNbr, String LineRef, ArrayList<CUSTOM_INVENTORY> custom_inventories){

        open_Database();
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (custom_inventories.size() > 0){

            for (int i = 0; i < custom_inventories.size(); i++){

                contentValues.put("OrderNbr", OderNbr);
                contentValues.put("LineRef", LineRef);
                contentValues.put("InvtID", custom_inventories.get(i).getmInvtID());
                contentValues.put("LineAmt", Double.valueOf(custom_inventories.get(i).getmTotalMoney()));
                contentValues.put("LineQty", Double.valueOf(custom_inventories.get(i).getmAmount()));

                sqLiteDatabase.insert("OM_SALESORDDET", null, contentValues);

            }

        }

        this.close();

    }

//    Phương thức insert data vào bảng OM_SALESORD
public void insert_OM_SALESORD(String OderNbr, CUSTOM_CUSTID_SlsperID id, ArrayList<CUSTOM_INVENTORY> custom_inventories, String OrdAmt, Double OrdQty, String OrdDate, String Remark){

    open_Database();
    sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();

    if (custom_inventories.size() > 0){

        for (int i = 0; i < custom_inventories.size(); i++){

            contentValues.put("OrderNbr", OderNbr);
            contentValues.put("SlsperID", id.getSlsperID());
            contentValues.put("CustID", id.getCustID());
            contentValues.put("OrdAmt", Double.valueOf(OrdAmt));
            contentValues.put("OrderQty", OrdQty);
            contentValues.put("OrderDate", OrdDate);
            contentValues.put("Remark", Remark);

            sqLiteDatabase.insert("OM_SALESORD", null, contentValues);

        }

    }

    this.close();

}

//    Phương thức lấy mã khách hàng và username
    /*public ArrayList<CUSTOM_CUSTID_SlsperID> get_CustID_SlsperID( CUSTOM_CUSTID_SlsperID id, ArrayList<CUSTOM_CUSTID_SlsperID> ids, String CustID){

        id = null;
        ids = new ArrayList<>();
        open_Database();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select distinct OM_SALESORD.CustID, OM_SALESORD.SlsperID from OM_SALESORD inner join AR_Customer\n" +
                "on OM_SALESORD.CustID = AR_Customer.CustID where OM_SALESORD.CustID = ?", new String[] {CustID});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            id = new CUSTOM_CUSTID_SlsperID(cursor.getString(0), cursor.getString(1));
            ids.add(id);
            cursor.moveToNext();
        }

        cursor.close();
        this.close();

        return ids;

    }

//    Phương thức lấy danh sách mã đơn hàng
    public  ArrayList<String> get_OderNbrs(ArrayList<String> OderNbrs){

        OderNbrs = new ArrayList<>();
        open_Database();
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select OM_SALESORD.OrderNbr from OM_SALESORD", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            OderNbrs.add(cursor.getString(0));
            cursor.moveToNext();

        }

        cursor.close();
        this.close();

        return OderNbrs;
    }
}
*/
