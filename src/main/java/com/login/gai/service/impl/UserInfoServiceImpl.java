package com.login.gai.service.impl;

import com.login.gai.dao.UserInfoMapper;
import com.login.gai.domain.UserInfo;
import com.login.gai.domain.UserInfoExample;
import com.login.gai.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/12/20.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int countByExample(UserInfoExample example) {
        return userInfoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserInfoExample example) {
        return userInfoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public List<UserInfo> selectByExample(UserInfoExample example) {
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(UserInfo record, UserInfoExample example) {
        return userInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(UserInfo record, UserInfoExample example) {
        return userInfoMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }
}
