package cn.code.test;

import cn.code.dao.UserDao;
import cn.code.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin(){
        /*
          User loginUser = new User();
        loginUser.setUsername("ironman");
        loginUser.setPassword("1213");

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        System.out.println(user);
         */

        User loginUser = new User();
        loginUser.setUsername("gentleman");
        loginUser.setPassword("222");

        UserDao dao = new UserDao();
        boolean flag = dao.add(loginUser);
        System.out.println(flag);

    }

}
