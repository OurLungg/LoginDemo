package cn.code.dao;

import cn.code.domain.User;
import cn.code.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 操作数据库中User表的类
 */
public class UserDao {

    //声明JDBCTemplate对象，以便方法公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     *
     * @param loginUser 目前只有用户名和密码
     * @return user包含用户全部数据
     */
    public User login(User loginUser) {
        //编写SQL
        String sql = "select * from user where username = ? and password = ?";
        //调用query方法
        try {
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 注册方法
     *
     * @param user 目前只包含用户名和密码
     * @return 是否注册成功
     */
    public boolean add(User user){
        //编写SQL
        String sql = "insert into user (username,password) VALUES('" + user.getUsername() + "','" + user.getPassword() + "')";
        boolean flag = false;
        int num = 0;
        try {
            //创建数据库连接
            Connection conn = JDBCUtils.getConnection();
            Statement state = null;
            flag = false;
            num = 0;

            state = conn.createStatement();
            num = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(num >0) flag = true;
        return flag;
    }
}

