package com.cy.store.service.impl;
import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result=userMapper.findByUsername(username);
        if(result!=null){
            throw new UnsupportedOperationException("用户名被占用");
        }
        String oldpassword=user.getPassword();
        String salt= UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Passwrod= getMD5Password(oldpassword,salt);
        user.setPassword(md5Passwrod);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("用户注册过程中产生了未知的错误");
        }
    }

    @Override
    public User login(String username, String password) {
        User result=userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("不存在数据");
        }
        String oldpassword=result.getPassword();
        String salt=result.getSalt();
        String newMd5=getMD5Password(password,salt);
        if(!newMd5.equals(oldpassword)){
            throw new PasswordNotMatchException("密码错误");
        }
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("不存在数据");
        }
        User user = new User();
// 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null||result.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String salt = result.getSalt();
        String oldMd5Password = getMD5Password(oldPassword, salt);
        if (!result.getPassword().contentEquals(oldMd5Password)) {
            throw new PasswordNotMatchException("原密码错误");
        }
        String newMd5Password = getMD5Password(newPassword, salt);
        Date now = new Date();
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username,
                now);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void changeallPassword(Integer uid, String username,String newPassword) {
        User result = userMapper.findByUid(uid);
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(newPassword, salt);
        Date now = new Date();
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username,
                now);
    }
    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password +
                    salt).getBytes()).toUpperCase();
        }//加密三次
        return password;
    }
    @Override
    public User getByUid(Integer uid) {

        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        if (result.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        if (result.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    public void changeallInfo(Integer uid, String username, User user) {
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }
    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }

        // 检查查询结果中的isDelete是否为1
        if (result.getIsDelete().equals(1)) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }

        // 创建当前时间对象
        Date now = new Date();
        // 调用userMapper的updateAvatarByUid()方法执行更新，并获取返回值
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, now);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getAllUsers();
    }


}
