package com.example.eliane.burkinaloi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliane on 2/12/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "bfloi.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.eliane.burkinaloi/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private static final int DATABASE_VERSION = 1;
    // Database Name
    //private static final String DATABASE_NAME = "Q";
    // tasks table name
    private static final String TABLE_QUEST = "QuestionLoi2";
    // tasks Table Columns names


    public DbHelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }
    public List<Question> getAllQuestions() {


        Question question = null;
        List<Question> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from QuestionLoi2 order by random()", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            question = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
            productList.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }

    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();

        return row;
    }
}

