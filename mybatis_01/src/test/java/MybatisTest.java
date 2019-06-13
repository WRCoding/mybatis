import com.lb.Dao.AccountMapper;
import com.lb.Dao.RoleMapper;
import com.lb.Dao.UserMapper;
import com.lb.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LB
 * @create 2019-06-09 16:01
 */
public class MybatisTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;
    private AccountMapper accountMapper;
    private RoleMapper roleMapper;

    @Before
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
        roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    @After
    public void destory() throws IOException {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }
    /**
     *测试查找所有
     * @throws Exception
     */
    @Test
    public void testFindAll() throws IOException {

        //使用代理对象执行方法
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void TestSave() throws IOException {
        User user = new User();
        user.setUserName("mybatis");
        user.setUserAddress("天津");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        userMapper.saveUser(user);

    }
    /**
     * 测试更新
     */
    @Test
    public void TestUpdate() throws IOException {
        User user = new User();
        user.setUserId(46);
        user.setUserName("林北柠檬");
        userMapper.updateUser(user);
    }
    /**
     * 测试删除
     */
    @Test
    public void TestDelete() throws IOException {
        userMapper.deleteUser(49);
    }
    /**
     * 测试通过id查找
     */
    @Test
    public void TestfindByid() throws IOException {
        User user = userMapper.findByid(46);
        System.out.println(user);
    }
    /**
     * 测试通过名字查找
     */
    @Test
    public void TestfindByName() throws IOException {
        List<User> userList = userMapper.findByName("王");
        for (User user : userList) {
            System.out.println(user);
        }
    }
    /**
     * 测试包装类
     */
    @Test
    public void TestfindByEmployee() throws IOException {
        Employee employee = new Employee();
        User user = new User();
        user.setUserName("%王%");
        employee.setUser(user);
        List<User> userList = userMapper.findByEmployee(employee);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }
    /**
     * 测试动态sql
     */
    @Test
    public void TestfindByCondition() throws IOException {
        User user = new User();
        user.setUserName("%王%");
        List<User> userList = userMapper.findByCondition(user);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }
    /**
     * 测试foreach
     */
    @Test
    public void TestfindByForEach() throws IOException {
        Employee employee = new Employee();
        List<Integer> idList = new ArrayList<>();
        idList.add(41);
        idList.add(42);
        idList.add(43);
        idList.add(45);
        employee.setIdList(idList);
        List<User> userList = userMapper.findByForEach(employee);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }
        /**
         * 测试查找账户
         */
        @Test
        public void testAccountFindAll(){
            List<Account> accountList = accountMapper.findAll();
            for (Account account : accountList) {
                System.out.println("----");
                System.out.println(account);
                System.out.println(account.getUser());
            }
        }
    /**
     * 测试通过账户查找用户信息和地址
     */
    @Test
    public void testAccountFindAccountUser(){
        List<AccountUser> accountUserList = accountMapper.findAllAccountUser();
        for (AccountUser account : accountUserList) {
            System.out.println(account);
        }
    }
    /**
     * 测试一对多
     */
    @Test
    public void testUserAccount(){
        List<User> userList = userMapper.findAccountUser();
        for (User user : userList) {
            System.out.println("----");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
    /**
     * 测试查询角色
     */
    @Test
    public void testRole(){
        List<Role> roleList = roleMapper.findAll();
        for (Role role : roleList) {
            System.out.println("-------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
}

