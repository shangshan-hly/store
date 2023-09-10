package com.cy.store.service.impl;
import com.cy.store.entity.Uuser;
import com.cy.store.mapper.UuserMapper;
import com.cy.store.service.IUuserService;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;
@Service
public class UuserServiceImpl implements IUuserService {
    @Autowired
    private UuserMapper userMapper;

    @Override
    public void reg(Uuser user) {
        String username = user.getUsername();
        Uuser result = userMapper.findByUsername(username);
        Date now = new Date();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);
        Integer rows = userMapper.insert(user);
    }

    @Override
    public Uuser login(String username, String password) {
        Uuser result = userMapper.findByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        String salt = result.getSalt();

        String md5Password = getMd5Password(password, salt);

        if (!result.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密码验证失败的错误");
        }
        Uuser user = new Uuser();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        Uuser result = userMapper.findByUid(uid);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        String salt = result.getSalt();
        String oldMd5Password = getMd5Password(oldPassword, salt);
        if (!result.getPassword().contentEquals(oldMd5Password)) {
            throw new PasswordNotMatchException("原密码错误");
        }
        String newMd5Password = getMd5Password(newPassword, salt);
        Date now = new Date();
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, now);
    }

    @Override
    public Uuser getByUid(Integer uid) {
        return null;
    }

    @Override
    public void changeInfo(Integer uid, String username, Uuser user) {

    }

    private String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}