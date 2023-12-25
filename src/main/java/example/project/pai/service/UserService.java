package example.project.pai.service;

import example.project.pai.dto.UserDto;
import example.project.pai.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}