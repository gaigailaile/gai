package com.login.gai.service.impl;

import com.login.gai.dao.AccountMapper;
import com.login.gai.domain.Account;
import com.login.gai.domain.AccountExample;
import com.login.gai.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/12/19.
 */
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int countByExample(AccountExample example) {
        return accountMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AccountExample example) {
        return accountMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    @Override
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    @Override
    public List<Account> selectByExample(AccountExample example) {
        return accountMapper.selectByExample(example);
    }

    @Override
    public Account selectByPrimaryKey(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Account record, AccountExample example) {
        return accountMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Account record, AccountExample example) {
        return accountMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Account record) {
        return accountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Account record) {
        return accountMapper.updateByPrimaryKey(record);
    }
}
