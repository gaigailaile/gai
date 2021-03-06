package com.login.gai.service;

import com.login.gai.domain.Account;
import com.login.gai.domain.AccountExample;
import com.login.gai.domain.AccountKey;

import java.util.List;

/**
 * Created by lenovo on 2018/12/19.
 */
public interface AccountService {
    int countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(AccountKey key);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(AccountKey key);

    int updateByExampleSelective(Account record, AccountExample example);

    int updateByExample(Account record, AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    boolean setValue(String key,String value);

    Object getValue(String key);
}
