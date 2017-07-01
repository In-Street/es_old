package cyf.es.search.service;

import cyf.es.search.dao.UserDao;
import cyf.es.search.domain.User;
import cyf.es.search.domain.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cheng on 2017/7/1.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void insert(Integer id){
        //构造sql语句条件
       UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
    }
}
