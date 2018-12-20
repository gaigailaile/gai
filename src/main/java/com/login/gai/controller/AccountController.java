package com.login.gai.controller;

import com.login.gai.domain.Account;
import com.login.gai.domain.AccountExample;
import com.login.gai.domain.Users;
import com.login.gai.domain.UsersExample;
import com.login.gai.service.AccountService;
import com.login.gai.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lenovo on 2018/12/19.
 */
@Api(value = "账号系统的相关差操作",tags = "Account",description = "Account接口",basePath = "/Account")
@RestController
@RequestMapping("${api.basePath}/Account")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;
    @Autowired
    private UsersService usersService;

    @ApiOperation("邮箱注册账号")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "account", paramType = "body", dataType = "Account", required = true, value="账号信息实体")
    })
    @CrossOrigin
    @PostMapping("/register")
    @Transactional
    public String register(@RequestBody Account account){
        try {
            Users users = new Users();
            users.setUsername(account.getAccount());
            UsersExample usersExample = new UsersExample();
            UsersExample.Criteria usersCriteria = usersExample.createCriteria();
            int result = usersService.insertSelective(users);
            if(result != 1){
                return "账号注册失败，请检查用户名";
            }

            account.setUsersId(users.getId());
            AccountExample accountExample = new AccountExample();
            AccountExample.Criteria accountCriteria = accountExample.createCriteria();
            int accountResult = accountService.insert(account);
            if(accountResult != 1){
                return "账号注册失败，请检查账号密码";
            }

        }catch (Exception e){
            logger.info("Exception 进行事务回滚"+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "注册失败";
        }finally {

        }
        return "注册成功";
    }

    @ApiOperation("登录校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", paramType = "body", dataType = "Account", required = true, value="账号信息实体")
    })
    @CrossOrigin
    @PostMapping("/SignIn")
    @Transactional
    public String SignIn(@RequestBody Account account){
        //以后添加验证码再此先进行校验

        //对用户密码进行校验
        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria accountCriteria = accountExample.createCriteria();
        accountCriteria.andAccountEqualTo(account.getAccount());
        accountCriteria.andPasswordEqualTo(account.getPassword());
        List<Account> list = accountService.selectByExample(accountExample);
        if(list == null){
            return "请检查账号密码";
        }

        //之后加入记住密码功能，考虑使用cookie,或浏览器的本地仓库
        return "登录成功";
    }
}
