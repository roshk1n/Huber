package com.example.roshk1n.test_hubert_dreyfus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB {
    public static final int DB_VERSION = 12;
    public static final String DB_NAME = "MyBD";
    public static final String DB_TABLE = "tabQuestion";
    private static final String TAG = "MyLog";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_FIRST_ANSWER = "first_answer";
    public static final String COLUMN_SECOND_ANSWER = "second_answer";
    public static final String COLUMN_THIRD_ANSWER = "third_answer";
    public static final String COLUMN_CATEGORY= "category";

    public User user;
    public static final String DB_TABLEUSR = "tabUser";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NOVICE = "novice";
    public static final String COLUMN_ADVANCED = "advanced_beginner";
    public static final String COLUMN_COMPETENT = "competent";
    public static final String COLUMN_PROFICIENT = "proficient";
    public static final String COLUMN_EXPERT = "expert";




    private   static final String DB_Create = "create table " + DB_TABLE + "(" + COLUMN_ID +
            " integer primary key autoincrement," + COLUMN_TEXT + " text," + COLUMN_FIRST_ANSWER + " text,"  + COLUMN_SECOND_ANSWER + " text," + COLUMN_THIRD_ANSWER + " text," + COLUMN_CATEGORY + " text" + ")";

    private static final String DB_CreateUsr= "create table " + DB_TABLEUSR + "(" + COLUMN_ID +
            " integer primary key autoincrement," + COLUMN_USERNAME + " text," + COLUMN_PASSWORD + " text,"  + COLUMN_NOVICE + " integer," + COLUMN_ADVANCED + " integer," + COLUMN_COMPETENT + " integer," + COLUMN_PROFICIENT + " integer," + COLUMN_EXPERT + " integer" + ")";

    private final Context mCtx;

    private DBHelper mDBHelper;

    public SQLiteDatabase mDB;

    public DB(Context ctx)
    {
        mCtx = ctx;
    }

    public void open()
    {
        mDBHelper= new DBHelper(mCtx,DB_NAME,null,DB_VERSION);
        mDB=mDBHelper.getWritableDatabase();
    }
    public void close()
    {
        if(mDBHelper !=null)
        {
            mDBHelper.close();
        }
    }

    public User getUser(User us)
    {

        String query = "select * from "+DB_TABLEUSR;
        Cursor cursor = mDB.rawQuery(query,null);

        cursor.moveToFirst();
        do {
            if(us.getUsername().equals(cursor.getString(1).toString())&&us.getPassword().equals(cursor.getString(2).toString())) {
                us.setValnovice(cursor.getInt(3));
                us.setValadvanced_beginer(cursor.getInt(4));
                us.setValcompetent(cursor.getInt(5));
                us.setValproficient(cursor.getInt(6));
                us.setValexpert(cursor.getInt(7));
                break;
            }
        }
        while (cursor.moveToNext());
        cursor.close();
        return us;
    }

    public void updateUser(User us)
    {
        ContentValues cv1= new ContentValues();
        cv1.put(COLUMN_USERNAME,us.getUsername());
        cv1.put(COLUMN_PASSWORD,us.getPassword());
        cv1.put(COLUMN_NOVICE,us.getValnovice());
        cv1.put(COLUMN_ADVANCED,us.getValadvanced_beginer());
        cv1.put(COLUMN_COMPETENT, us.getValcompetent());
        cv1.put(COLUMN_PROFICIENT, us.getValproficient());
        cv1.put(COLUMN_EXPERT, us.getValexpert());
        mDB.update("tabUser",cv1,"username = ?",new String[] {us.getUsername()});
    }

    public void inserUser(User user)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME,user.getUsername());
        contentValues.put(COLUMN_PASSWORD,user.getPassword());
        contentValues.put(COLUMN_NOVICE,user.getValnovice());
        contentValues.put(COLUMN_ADVANCED,user.getValadvanced_beginer());
        contentValues.put(COLUMN_COMPETENT, user.getValcompetent());
        contentValues.put(COLUMN_PROFICIENT, user.getValproficient());
        contentValues.put(COLUMN_EXPERT, user.getValexpert());

        mDB.insert(DB_TABLEUSR,null,contentValues);
    }
    public boolean verification(String username,String pas)
    {
        boolean check=false;
        String query = "select username, password from "+DB_TABLEUSR;
        Cursor cursor = mDB.rawQuery(query,null);

        cursor.moveToFirst();
        do {
            if(username.equals(cursor.getString(0).toString())&&pas.equals(cursor.getString(1).toString())) {
                check = true;
                break;
            }
        }
        while (cursor.moveToNext());

        return check;
    }
    public Cursor getAllData(String category)
    {
        return mDB.rawQuery("select * from tabQuestion where category = ?", new String[] { category });

    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context con,String name,SQLiteDatabase.CursorFactory factory,int version)
        {
            super(con,name,factory,version);
        }
        //Створюємо БД
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DB_Create);
            db.execSQL(DB_CreateUsr);

            ContentValues cv2= new ContentValues();
            cv2.put(COLUMN_USERNAME,"roshk1n");
            cv2.put(COLUMN_PASSWORD,"132132");
            cv2.put(COLUMN_NOVICE,0);
            cv2.put(COLUMN_ADVANCED,0);
            cv2.put(COLUMN_COMPETENT,0);
            cv2.put(COLUMN_PROFICIENT, 0);
            cv2.put(COLUMN_EXPERT, 0);

            db.insert(DB_TABLEUSR, null, cv2);

            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TEXT,"1. Переживаєте за успіх в роботі?");
            cv.put(COLUMN_FIRST_ANSWER,"сильно(5)");
            cv.put(COLUMN_SECOND_ANSWER,"не дуже(3)");
            cv.put(COLUMN_THIRD_ANSWER, "спокійний(2)");
            cv.put(COLUMN_CATEGORY, "Novice");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "2. Прагнете досягти швидко результату?");
            cv.put(COLUMN_FIRST_ANSWER,"дуже(5)");
            cv.put(COLUMN_SECOND_ANSWER,"якомога швидше(3)");
            cv.put(COLUMN_THIRD_ANSWER,"поступово(2)");
            cv.put(COLUMN_CATEGORY, "Novice");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "3. Легко попадаєте в тупик при проблемах в роботі?");
            cv.put(COLUMN_FIRST_ANSWER,"неодмінно(5)");
            cv.put(COLUMN_SECOND_ANSWER,"поступово(3)");
            cv.put(COLUMN_THIRD_ANSWER,"зрідка(2)");
            cv.put(COLUMN_CATEGORY, "Novice");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "4. Чи потрібен чіткий алгоритм для вирішення задач?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"в окрмих випадках(3)");
            cv.put(COLUMN_THIRD_ANSWER,"непотрібен(2)");
            cv.put(COLUMN_CATEGORY, "Novice");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "1. Чи використовуєте власний досвід при вирішенні задач?");
            cv.put(COLUMN_FIRST_ANSWER,"зрідка(5)");
            cv.put(COLUMN_SECOND_ANSWER,"частково(3)");
            cv.put(COLUMN_THIRD_ANSWER,"ні(2)");
            cv.put(COLUMN_CATEGORY, "Advanced beginner");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "2. Чи користуєтесь фіксованими правилами  для вирішення задач?");
            cv.put(COLUMN_FIRST_ANSWER,"не потрібні(5)");
            cv.put(COLUMN_SECOND_ANSWER,"в окрмих випадках(3)");
            cv.put(COLUMN_THIRD_ANSWER,"так(2)");
            cv.put(COLUMN_CATEGORY, "Advanced beginner");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "3. Чи відчуваєте ви загальний контекст вирішення задачі?");
            cv.put(COLUMN_FIRST_ANSWER,"в окремих випадках(5)");
            cv.put(COLUMN_SECOND_ANSWER,"частково(3)");
            cv.put(COLUMN_THIRD_ANSWER,"так(2)");
            cv.put(COLUMN_CATEGORY, "Advanced beginner");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "1. Чи можете ви побудувати модель вирішуваної задачі?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"не повністю(3)");
            cv.put(COLUMN_THIRD_ANSWER,"в окремих випадках(2)");
            cv.put(COLUMN_CATEGORY, "Competent");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "2. Чи вистачає вам ініціативи при вирішенні задач?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"зрідка(3)");
            cv.put(COLUMN_THIRD_ANSWER,"потрібне натхнення(2)");
            cv.put(COLUMN_CATEGORY,"Competent");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "3. Чи можете вирішувати проблеми, з якими ще не стикались?");
            cv.put(COLUMN_FIRST_ANSWER,"ні(5)");
            cv.put(COLUMN_SECOND_ANSWER,"в окремих випадках(3)");
            cv.put(COLUMN_THIRD_ANSWER,"так(2)");
            cv.put(COLUMN_CATEGORY,"Competent");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "1. Чи  необхідний вам весь контекст задачі?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"в окрмих деталях(3)");
            cv.put(COLUMN_THIRD_ANSWER,"в загальному(2)");
            cv.put(COLUMN_CATEGORY,"Proficient");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "2. Чи переглядаєте ви свої наміри до вирішення задачі?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"зрідка(3)");
            cv.put(COLUMN_THIRD_ANSWER,"коли є потреба(2)");
            cv.put(COLUMN_CATEGORY,"Proficient");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "3. Чи здатні  ви  навчатись у інших?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"зрідка(3)");
            cv.put(COLUMN_THIRD_ANSWER,"коли є потреба(2)");
            cv.put(COLUMN_CATEGORY,"Proficient");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "1. Чи обираєте ви нові методи своєї роботи?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"вибірково(3)");
            cv.put(COLUMN_THIRD_ANSWER,"вистачає досвіду(2)");
            cv.put(COLUMN_CATEGORY,"Expert");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "2. Чи допомагає власна інтуїція при вирішенні задач?");
            cv.put(COLUMN_FIRST_ANSWER,"так(5)");
            cv.put(COLUMN_SECOND_ANSWER,"частково(3)");
            cv.put(COLUMN_THIRD_ANSWER,"при емоційному напружені(2)");
            cv.put(COLUMN_CATEGORY,"Expert");
            db.insert(DB_TABLE, null, cv);

            cv.put(COLUMN_TEXT, "3. Чи застовуєте рішення задач за аналогією?");
            cv.put(COLUMN_FIRST_ANSWER,"частково(5)");
            cv.put(COLUMN_SECOND_ANSWER,"зрідка(3)");
            cv.put(COLUMN_THIRD_ANSWER,"тільки власний варіант(2)");
            cv.put(COLUMN_CATEGORY,"Expert");
            db.insert(DB_TABLE, null, cv);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE tabQuestion");
            db.execSQL("DROP TABLE tabUser");
            onCreate(db);
        }
    }
}

