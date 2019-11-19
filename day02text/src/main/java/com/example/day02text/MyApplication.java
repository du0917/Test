package com.example.day02text;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class MyApplication extends Application {

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // 应用启动仅执行一次，适合做应用初始化工作(只需要执行一次)
        // 初始化GreenDao
        daoSession = initGreenDao();
    }

    private DaoSession initGreenDao() {
        // 初始化DevOpenHelper
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(
                getApplicationContext(),
                "student.db");
        // 数据库对象
        SQLiteDatabase database = openHelper.getReadableDatabase();
        // 创建DaoMaster，用来创建DaoSession
        DaoMaster daoMaster = new DaoMaster(database);
        // DaoSession管理各种Dao类
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }
}