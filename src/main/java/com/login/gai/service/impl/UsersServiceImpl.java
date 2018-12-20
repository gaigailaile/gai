package com.login.gai.service.impl;

import com.login.gai.dao.UsersMapper;
import com.login.gai.domain.Users;
import com.login.gai.domain.UsersExample;
import com.login.gai.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/12/20.
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public int countByExample(UsersExample example) {
        return usersMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UsersExample example) {
        return usersMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {
        return usersMapper.selectByExample(example);
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Users record, UsersExample example) {
        return usersMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Users record, UsersExample example) {
        return usersMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }
}
