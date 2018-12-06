package com.dragger2.reactnativetest1022.base.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dragger2.reactnativetest1022.base.greendao.gen.DaoMaster;
import com.dragger2.reactnativetest1022.base.greendao.gen.DaoSession;
import com.dragger2.reactnativetest1022.base.greendao.utils.MySQLiteOpenHelper;

/**
 * 数据库
 * Created by wangru
 * Date: 2017/12/20  9:47
 * mail: 1902065822@qq.com
 * describe:
 */
public class DbManager {

    public static final boolean ENCRYPTED = true;

    private static DbManager mDbManager;
    private MySQLiteOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private Context mContext;
    private int userId;


    public static DbManager instance() {
        if (mDbManager == null) {
            synchronized (DbManager.class) {
                if (mDbManager == null) {
                    mDbManager = new DbManager();
                }
            }
        }
        return mDbManager;
    }

    public void initDB(Context context) {
        this.mContext = context;
        this.userId = userId;
        // 初始化数据库信息
        String dbName = "yingba.db";
        mDevOpenHelper = new MySQLiteOpenHelper(context, dbName, null);
    }


    /**
     * 获取可读数据库
     *
     * @return
     */
    public SQLiteDatabase getReadableDatabase() {
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    public SQLiteDatabase getWritableDatabase() {
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        mDaoMaster = new DaoMaster(getWritableDatabase());
        return mDaoMaster;
    }

    /**
     * 获取DaoSession
     *
     * @return
     */
    public DaoSession getDaoSession() {
        mDaoSession = getDaoMaster().newSession();
        return mDaoSession;
    }
}