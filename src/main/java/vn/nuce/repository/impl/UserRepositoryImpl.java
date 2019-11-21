package vn.nuce.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.nuce.data.impl.CrudRepositoryImpl;
import vn.nuce.entity.UserEntity;
import vn.nuce.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl extends CrudRepositoryImpl<Long, UserEntity> implements UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Object[] checkLogin(String username, String password) {
        boolean isSuccess = false;
        UserEntity entity = null;
        try {
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append(getPersistenceClassName());
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                sql.append(" WHERE username =:username AND password =:password");
            }
            Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                query.setParameter("username", username);
                query.setParameter("password", password);
            }
            entity = (UserEntity) query.uniqueResult();
            if (entity != null)
                isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[]{isSuccess, entity};
    }
}
