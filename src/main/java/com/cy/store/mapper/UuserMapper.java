package com.cy.store.mapper;
import com.cy.store.entity.Uuser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/** 处理用户数据操作的持久层接口 */
public interface UuserMapper {

    Integer insert(Uuser user);

    Uuser findByUsername(String username);

    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
    Uuser findByUid(Integer uid);

    Integer updateInfoByUid(Uuser user);


}

