package com.cy.store.mapper.service;
import com.cy.store.entity.Uuser;
import com.cy.store.service.IUuserService;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUuserService iUserService;

    @Test
    public void reg() {
        try {
            Uuser user = new Uuser();
            user.setUsername("lower1");
            user.setPassword("123456");
            iUserService.reg(user);
            System.out.println("注册成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}

