package com.android.basics.data.repository;

import com.android.basics.core.Callback;
import com.android.basics.data.component.DaoCallback;
import com.android.basics.data.component.DaoExecutor;
import com.android.basics.data.mapper.UserMapper;
import com.android.basics.data.source.dao.UserDao;
import com.android.basics.domain.model.User;
import com.android.basics.domain.repository.UserRepository;

public class UserDataRespository implements UserRepository {

    private UserDao userDao;
    private UserMapper userMapper;
    private DaoExecutor daoExecutor;

    public UserDataRespository(DaoExecutor daoExecutor, UserDao userDao, UserMapper userMapper) {
        this.daoExecutor = daoExecutor;
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public void authenticate(String userName, String password, Callback<User> callback) {
        DaoCallback daoCallback = () -> {
            User response = userMapper.convert(userDao.getUser(userName, password));
            if (response != null) {
                callback.onResponse(response);
            } else {
                callback.onError("00002", "No data available");
            }
        };
        daoExecutor.start(daoCallback);
    }

    @Override
    public void register(String userName, String password, Callback<User> callback) {
        DaoCallback daoCallback = () -> {
            userDao.insert(userName, password);
            User response = userMapper.convert(userDao.getUser(userName, password));
            if (response != null) {
                callback.onResponse(response);
            } else {
                callback.onError("00002", "No data available");
            }
        };
        daoExecutor.start(daoCallback);
    }
}
