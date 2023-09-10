package com.cy.store.service;
import com.cy.store.entity.Uuser;

public interface IUuserService {
    void reg(Uuser user);
    Uuser login (String username,String password);
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword);
    Uuser getByUid(Integer uid);
    void changeInfo(Integer uid, String username, Uuser user);

}
