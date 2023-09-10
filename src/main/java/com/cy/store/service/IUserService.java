package com.cy.store.service;
import com.cy.store.entity.User;

import java.util.List;

public interface IUserService {
    void reg(User user);
    User login (String username,String password);
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword);

    public void changeallPassword(Integer uid,
                               String username,
                               String newPassword);
    User getByUid(Integer uid);
    void changeInfo(Integer uid, String username, User user);
    void changeallInfo(Integer uid, String username, User user);
    void changeAvatar(Integer uid, String username, String avatar);


    List<User> getUsers();

}
