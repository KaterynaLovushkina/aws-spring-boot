package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.AppCategory;
import com.lovushkina.store.domain.User;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.UserRepository;
import com.lovushkina.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User", id));
    }

    @Override
    public User create(User entity) {
        userRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, User entity) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User", id));
        user.setPasswordHash(entity.getPasswordHash());
        user.setFullName(entity.getFullName());
        user.setCountry(entity.getCountry());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createTablesWithСursor() {
        userRepository.createTablesWithСursor();
    }
}
