import com.lb.dao.AccountMapper;
import com.lb.dao.UserMapper;
import com.lb.pojo.Account;
import com.lb.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author LB
 * @create 2019-06-13 7:55
 */
public class mybatisAnnoTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;
    private AccountMapper accountMapper;

    @Before//测试执行之前执行
    public void init() throws Exception {
        //读取配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //生产SqlSession对象
        sqlSession = factory.openSession();
        //使用sqlsession创建Dao接口的代理对象
        userMapper =  sqlSession.getMapper(UserMapper.class);
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }
    @After//测试执行之后执行
    public void destory() throws IOException {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

    /**
     * 测试注解
     */
    @Test
    public void testAnno(){
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("pepsi");
        user.setUserSex("女");
        user.setUserAddress("美国");
        user.setUserBirthday(new Date());
        userMapper.saveUser(user);
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(48);
        user.setUserName("可口可乐");
        userMapper.updateUser(user);
    }
    @Test
    public void testdelete(){
       userMapper.deleteUser(50);
    }
    @Test
    public void testOneToOne(){
        List<Account> accountList = accountMapper.findAll();
        for (Account account : accountList) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
    @Test
    public void testOneToMany(){
        List<User> userList = userMapper.findAllByAccount();
        for (User user : userList) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
}
