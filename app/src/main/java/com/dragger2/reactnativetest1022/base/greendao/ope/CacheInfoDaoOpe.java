package com.dragger2.reactnativetest1022.base.greendao.ope;

import android.text.TextUtils;

import com.dragger2.reactnativetest1022.base.greendao.DbManager;
import com.dragger2.reactnativetest1022.base.greendao.bean.CacheInfoBean;
import com.dragger2.reactnativetest1022.base.greendao.gen.CacheInfoBeanDao;


/**
 * 缓存操作
 * Created by wangru
 * Date: 2018/1/11  16:03
 * mail: 1902065822@qq.com
 * describe:
 */

public class CacheInfoDaoOpe {

    private static CacheInfoBeanDao getCacheInfoDao() {
        return DbManager.instance().getDaoSession().getCacheInfoBeanDao();
    }


    public static String getVersionCodeByKey(String key) {
        CacheInfoBean cacheInfo = getCacheInfoByKey(key);
        if (cacheInfo != null) {
            return cacheInfo.getVersion();
        }
        return null;
    }

    public static void insertOrUpdateVersion(CacheInfoBean cacheInfoBean) {
        if (cacheInfoBean != null && !TextUtils.isEmpty(cacheInfoBean.getKey())) {
            insertOrUpdateVersionByKey(cacheInfoBean.getKey(), cacheInfoBean);
        }
    }

    public static void insertOrUpdateVersionByKey(String key, CacheInfoBean cacheInfoBean) {
        if (cacheInfoBean == null) {
            return;
        }
        cacheInfoBean.setKey(key);
        CacheInfoBean cacheInfo = getCacheInfoByKey(key);
        if (cacheInfo != null) {
            cacheInfoBean.set_id(cacheInfo.get_id());
        }
        getCacheInfoDao().save(cacheInfoBean);
    }

    public static CacheInfoBean getCacheInfoByKey(String key) {
        return getCacheInfoDao().queryBuilder().where(CacheInfoBeanDao.Properties.Key.eq(key)).limit(1).build().unique();
    }

    public static String getCacheInfoDataByKey(String key) {
        CacheInfoBean cacheInfo = getCacheInfoByKey(key);
        if (cacheInfo != null) {
            return cacheInfo.getData();
        }
        return null;
    }

}
