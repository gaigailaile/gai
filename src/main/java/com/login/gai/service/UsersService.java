package com.login.gai.service;

import com.login.gai.domain.Users;
import com.login.gai.domain.UsersExample;

import java.util.List;

/**
 * Created by lenovo on 2018/12/20.
 */
public interface UsersService {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Users record, UsersExample example);

    int updateByExample(Users record, UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}
