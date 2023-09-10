package com.cy.store.mapper;
import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/** 处理用户数据操作的持久层接口 */
public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);


    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    List<User> getAllUsers();

}

