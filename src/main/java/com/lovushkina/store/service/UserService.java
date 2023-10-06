package com.lovushkina.store.service;

import com.lovushkina.store.domain.User;

public interface UserService extends GeneralService<User, Integer> {
    void createTablesWithСursor();
}
