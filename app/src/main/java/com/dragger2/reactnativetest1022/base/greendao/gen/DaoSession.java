package com.dragger2.reactnativetest1022.base.greendao.gen;

import com.dragger2.reactnativetest1022.base.greendao.bean.CacheInfoBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cacheInfoBeanDaoConfig;

    private final CacheInfoBeanDao cacheInfoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cacheInfoBeanDaoConfig = daoConfigMap.get(CacheInfoBeanDao.class).clone();
        cacheInfoBeanDaoConfig.initIdentityScope(type);

        cacheInfoBeanDao = new CacheInfoBeanDao(cacheInfoBeanDaoConfig, this);

        registerDao(CacheInfoBean.class, cacheInfoBeanDao);
    }
    
    public void clear() {
        cacheInfoBeanDaoConfig.clearIdentityScope();
    }

    public CacheInfoBeanDao getCacheInfoBeanDao() {
        return cacheInfoBeanDao;
    }

}
