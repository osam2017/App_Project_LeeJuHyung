package com.example.administrator.project;
/**
 * Created by Administrator on 2017/10/17. 사용자정보관리
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class UserDataManager {             //데이터베이스
    //공통 선언
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "user_data";
    private static final String TABLE_NAME = "users";
    public static final String ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    //    public static final String SILENT = "silent";
//    public static final String VIBRATE = "vibrate";
    private static final int DB_VERSION = 2;
    private Context mContext = null;

    //사용자book표 생성
    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " integer primary key," + USER_NAME + " varchar,"
            + USER_PWD + " varchar" + ");";

    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;

    //DataBaseManagementHelper 상속 SQLiteOpenHelper
    private static class DataBaseManagementHelper extends SQLiteOpenHelper {

        DataBaseManagementHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "db.getVersion()=" + db.getVersion());
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
            Log.i(TAG, "db.execSQL(DB_CREATE)");
            Log.e(TAG, DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "DataBaseManagementHelper onUpgrade");
            onCreate(db);
        }
    }

    public UserDataManager(Context context) {
        mContext = context;
        Log.i(TAG, "UserDataManager construction!");
    }

    //데이터베이스 열기
    public void openDataBase() throws SQLException {
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }

    //데이터 베이스 닫기
    public void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

    //새로추가하기，그리고 로그인
    public long insertUserData(UserData userData) {
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        return mSQLiteDatabase.insert(TABLE_NAME, ID, values);
    }

    //사용자 정보 새로고침，ex:비밀번호 변경
    public boolean updateUserData(UserData userData) {
        //int id = userData.getUserId();
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        return mSQLiteDatabase.update(TABLE_NAME, values, null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }

    //
    public Cursor fetchUserData(int id) throws SQLException {
        Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME, null, ID
                + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //
    public Cursor fetchAllUserDatas() {
        return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null,
                null);
    }

    //id를 통해 사용자 삭제
    public boolean deleteUserData(int id) {
        return mSQLiteDatabase.delete(TABLE_NAME, ID + "=" + id, null) > 0;
    }

    //사용자명을 통해 삭제
    public boolean deleteUserDatabyname(String name) {
        return mSQLiteDatabase.delete(TABLE_NAME, USER_NAME + "=" + name, null) > 0;
    }

    //모두 삭제
    public boolean deleteAllUserDatas() {
        return mSQLiteDatabase.delete(TABLE_NAME, null, null) > 0;
    }

    //
    public String getStringByColumnName(String columnName, int id) {
        Cursor mCursor = fetchUserData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);
        String columnValue = mCursor.getString(columnIndex);
        mCursor.close();
        return columnValue;
    }

    //
    public boolean updateUserDataById(String columnName, int id,
                                      String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }

    //사용자명을 통해 사용자 찾기，회원가입시 사용자명 중복 확인
    public int findUserByName(String userName) {
        Log.i(TAG, "findUserByName , userName=" + userName);
        int result = 0;
        try {
            Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName, null, null, null, null);

            if (mCursor != null) {
                result = mCursor.getCount();
                mCursor.close();
                Log.i(TAG, "findUserByName , result=" + result);
            }
        } catch (Exception e) {
            Log.i(TAG, "아이디중복커서 오류");
        }
        return result;
    }

    //아이디 비밀번호 확인을 통한 로그인
    public int findUserByNameAndPwd(String userName, String pwd) {
        Log.i(TAG, "findUserByNameAndPwd");
        int result = 0;
        try {
            Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName + " and " + USER_PWD + "=" + pwd,
                    null, null, null, null);
            if (mCursor != null) {
                result = mCursor.getCount();
                mCursor.close();
                Log.i(TAG, "findUserByNameAndPwd , result=" + result);
            }
        } catch(Exception e) {
            Log.i(TAG, "아이디 비번 확인 커서 오류");
        }

        return result;
    }

}
