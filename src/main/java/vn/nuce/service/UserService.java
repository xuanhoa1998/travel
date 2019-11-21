package vn.nuce.service;

import vn.nuce.dto.UserDto;

import java.util.List;

public interface UserService {
    Object[] checkLogin(String username,String password);
    List<UserDto> findAllUsers();
}
