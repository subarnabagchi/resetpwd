package com.app.resetpwd.service;

import com.app.resetpwd.model.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> findByEmailId(String email);

    public void save(User user);
}
