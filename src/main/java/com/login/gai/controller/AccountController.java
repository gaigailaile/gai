package com.login.gai.controller;

import com.login.gai.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2018/12/19.
 */
@RestController
@RequestMapping("${api.basePath}/Account")
@Api(value = "账号系统的相关差操作")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping()
    public String register(){
        return null;
    }
}
