package com.codegym.excercise_registration_form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codegym.excercise_registration_form.model.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
}