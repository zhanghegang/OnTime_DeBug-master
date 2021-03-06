package zhanghegang.com.bawei.onetime.bean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import zhanghegang.com.bawei.onetime.bean.VerSionDownTable;

import zhanghegang.com.bawei.onetime.bean.VerSionDownTableDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig verSionDownTableDaoConfig;

    private final VerSionDownTableDao verSionDownTableDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        verSionDownTableDaoConfig = daoConfigMap.get(VerSionDownTableDao.class).clone();
        verSionDownTableDaoConfig.initIdentityScope(type);

        verSionDownTableDao = new VerSionDownTableDao(verSionDownTableDaoConfig, this);

        registerDao(VerSionDownTable.class, verSionDownTableDao);
    }
    
    public void clear() {
        verSionDownTableDaoConfig.clearIdentityScope();
    }

    public VerSionDownTableDao getVerSionDownTableDao() {
        return verSionDownTableDao;
    }

}
