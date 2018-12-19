package com.login.gai.controller;

import com.login.gai.dao.AccountMapper;
import com.login.gai.domain.Account;
import com.login.gai.domain.AccountExample;
import com.login.gai.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lenovo on 2018/12/19.
 */
@RestController
@RequestMapping("${api.basePath}/Account")
@Api(value = "账号系统的相关差操作")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/register")
    public String register(@RequestBody Account account){
        List<Account> list = accountService.selectByUserName(account.getUsername());
        if(list != null){
            return "用户名不可用";
        }

        return null;
    }
}
