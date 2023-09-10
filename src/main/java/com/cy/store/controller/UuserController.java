package com.cy.store.controller;
import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileStateException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.entity.User;
import com.cy.store.entity.Uuser;
import com.cy.store.service.IUuserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.cy.store.controller.BaseController.OK;

@RestController
@RequestMapping("uusers")
public class UuserController {
    @Autowired
    private IUuserService userService;
    @RequestMapping("login")
    public JsonResult<Uuser> login(String username, String password, HttpSession session) {
        Uuser data = userService.login(username, password);

        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return new JsonResult<Uuser>(OK, data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}