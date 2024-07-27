package com.codegym.excercise_registration_form.service;

import com.codegym.excercise_registration_form.model.User;

import java.util.List;

public interface IUserService {
    void save(User user);

    List<User> findAll();
}