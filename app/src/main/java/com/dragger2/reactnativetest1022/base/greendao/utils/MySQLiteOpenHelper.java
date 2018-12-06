package com.dragger2.reactnativetest1022.base.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dragger2.reactnativetest1022.base.greendao.gen.CacheInfoBeanDao;
import com.dragger2.reactnativetest1022.base.greendao.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * 升级工具  添加 XXXDao.class
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, CacheInfoBeanDao.class);
    }
}