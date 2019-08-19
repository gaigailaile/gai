package com.login.gai.service;

import com.login.gai.domain.UserInfo;
import com.login.gai.domain.UserInfoExample;

import java.util.List;

/**
 * Created by lenovo on 2018/12/20.
 */
public interface UserInfoService {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(UserInfo record, UserInfoExample example);

    int updateByExample(UserInfo record, UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
