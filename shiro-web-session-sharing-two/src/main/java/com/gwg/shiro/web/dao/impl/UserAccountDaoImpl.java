package com.gwg.shiro.web.dao.impl;

import com.gwg.shiro.web.dao.UserAccountDao;
import com.gwg.shiro.web.mapper.UserAccountMapper;
import com.gwg.shiro.web.model.UserAccount;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class UserAccountDaoImpl implements UserAccountDao {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public UserAccount queryUserAccountByUserid(String userid) {
        if(StringUtils.isEmpty(userid)){
            return null;
        }
        Example example = new Example(UserAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid", userid);
        List<UserAccount> userList = userAccountMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }

        return userList.get(0);
    }
}
