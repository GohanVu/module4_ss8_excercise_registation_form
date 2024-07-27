package com.codegym.excercise_registration_form.dto;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jakarta.validation.constraints.*;

public class UserDto implements Validator {
    private Integer id;

    @NotEmpty(message = "Nhấc cái tay lên, điền vào đi")
    private String firstName;
    @NotEmpty(message = "Nhấc cái tay lên, điền vào đi")
    private String lastname;
    @NotEmpty(message = "Nhấc cái tay lên, điền vào đi")
    private String phoneNumber;
    @NotNull(message = "Nhấc cái tay lên, điền vào đi")
    @Range(min = 18, max = 100)
    private Integer age;
    @NotEmpty(message = "Nhấc cái tay lên, điền vào đi")
    private String email;

    public UserDto() {
    }

    public UserDto(Integer id, String firstName, String lastname, String phoneNumber, Integer age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if (!userDto.getFirstName().isEmpty()) {
            if (userDto.getFirstName().length() < 4 || userDto.getFirstName().length() > 46) {
                errors.rejectValue("firstName", "firstname.regex.failed", "Tên chỉ được có từ 5 đến 45 kí tự");
            }
        }
        if (!userDto.getLastname().isEmpty()) {
            if (userDto.getLastname().length() < 4 || userDto.getFirstName().length() > 46) {
                errors.rejectValue("lastname", "lastname.regex.failed", "Họ chỉ được có từ 5 đến 45 kí tự");
            }
        }
        if (!userDto.getPhoneNumber().isEmpty()) {
            if (!userDto.getPhoneNumber().matches("^0\\d{9}|[(]84[)][+]\\d{9}$")) {
                errors.rejectValue("phoneNumber", "phoneNumber.regex.failed", "Không đúng định dạng á nghe");
            }
        }

        if (!userDto.getEmail().isEmpty()) {
            if (!userDto.getEmail().matches("^[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")) {
                errors.rejectValue("email", "email.regex.failed", "Email cần đúng định dạng");
            }
        }


    }
}