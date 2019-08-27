package com.login.gai.service.impl;

import com.login.gai.dao.AccountMapper;
import com.login.gai.domain.Account;
import com.login.gai.domain.AccountExample;
import com.login.gai.domain.AccountKey;
import com.login.gai.service.AccountService;
import com.login.gai.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/12/19.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public int countByExample(AccountExample example) {
        return accountMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AccountExample example) {
        return accountMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(AccountKey key) {
        return accountMapper.deleteByPrimaryKey(key);
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
    public Account selectByPrimaryKey(AccountKey key) {
        return accountMapper.selectByPrimaryKey(key);
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

    @Override
    public boolean setValue(String key, String value) {
        return redisUtil.set(key, value);
    }

    @Override
    public Object getValue(String key) {
        return redisUtil.get(key);
    }
}
