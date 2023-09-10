package com.cy.store.controller;
import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.service.ex.FileUploadException;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpSession;
public class BaseController {
    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if (e instanceof UsernameDuplicateException) {
            result.setState(5001);
            result.setMessage("用户名不存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("密码错误");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入数据时发生了一个错误");
        }else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新数据时产生一个错误");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
        }else if (e instanceof DeleteException) {
            result.setState(5002);
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
        }
        return result;
    }
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
