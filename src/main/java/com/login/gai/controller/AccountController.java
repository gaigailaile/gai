package com.login.gai.controller;

import com.login.gai.domain.Account;
import com.login.gai.domain.AccountExample;
import com.login.gai.domain.Users;
import com.login.gai.domain.UsersExample;
import com.login.gai.service.AccountService;
import com.login.gai.service.UsersService;
import com.login.gai.utils.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lenovo on 2018/12/19.
 */
@Api(value = "账号系统的相关差操作", tags = "Account", description = "Account接口", basePath = "/Account")
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UsersService usersService;

    @ApiOperation("邮箱注册账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", paramType = "body", dataType = "Account", required = true, value = "账号信息实体")
    })
    @CrossOrigin
    @PostMapping("/register")
    @Transactional
    public String register(@RequestBody Account account) {
        try {
            Users users = new Users();
            users.setUsername(account.getAccount());
            UsersExample usersExample = new UsersExample();
            UsersExample.Criteria usersCriteria = usersExample.createCriteria();
            int result = usersService.insertSelective(users);
            if (result != 1) {
                return "账号注册失败，请检查用户名";
            }

            account.setUsersId(users.getId());
            AccountExample accountExample = new AccountExample();
            AccountExample.Criteria accountCriteria = accountExample.createCriteria();
            int accountResult = accountService.insert(account);
            if (accountResult != 1) {
                return "账号注册失败，请检查账号密码";
            }

        } catch (Exception e) {
            log.info("Exception 进行事务回滚{}", e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "注册失败";
        } finally {

        }
        return "注册成功";
    }

    @ApiOperation("登录校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", paramType = "body", dataType = "Account", required = true, value = "账号信息实体")
    })
    @CrossOrigin
    @PostMapping("/signIn")
    @Transactional
    public String signIn(@RequestBody Account account) {
        //以后添加验证码再此先进行校验

        //对用户密码进行校验
        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria accountCriteria = accountExample.createCriteria();
        accountCriteria.andAccountEqualTo(account.getAccount());
        accountCriteria.andPasswordEqualTo(account.getPassword());
        List<Account> list = accountService.selectByExample(accountExample);
        if (list == null) {
            return "请检查账号密码";
        }

        //之后加入记住密码功能，考虑使用cookie,或浏览器的本地仓库
        return "登录成功";
    }


    @ApiOperation("校验用户名、手机号、邮箱是否被使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", paramType = "query", dataType = "String", required = true)
    })
    @CrossOrigin
    @GetMapping("/checkAccount")
    @Transactional
    public ResponseModel checkAccount(@RequestParam String account) {
        //检查账号、用户名、手机号是否被使用
        ResponseModel responseModel = new ResponseModel();
        AccountExample accountExample = new AccountExample();
        AccountExample.Criteria accountCriteria = accountExample.createCriteria();
        accountCriteria.andAccountEqualTo(account);
        List<Account> list = accountService.selectByExample(accountExample);
        if (list != null && list.size() > 0) {
            responseModel.setStatus(true);
            responseModel.setMessage("账号以注册，不能再次注册");
        } else {
            responseModel.setStatus(true);
            responseModel.setMessage("账号可以使用");
        }
        return responseModel;
    }

    @CrossOrigin
    @GetMapping("/test")
    @Transactional
    public ResponseModel test() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(true);
        responseModel.setMessage("测试点及");
        System.out.println(".......");
        return responseModel;
    }

}
