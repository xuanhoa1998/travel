package vn.nuce.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.nuce.dto.UserDto;
import vn.nuce.repository.impl.UserRepositoryImpl;
import vn.nuce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoryImpl repository;

    private ModelMapper mapper = null;

    private ModelMapper getInstanceMapper() {
        if (mapper == null) {
            mapper = new ModelMapper();
        }
        return mapper;
    }

    @Override
    public Object[] checkLogin(String username, String password) {
        UserDto dto = null;
        Object[] objects = repository.checkLogin(username, password);
        boolean isSuccess = (boolean) objects[0];
        if (isSuccess) {
            dto = getInstanceMapper().map(objects[1], UserDto.class);
        }
        return new Object[]{isSuccess, dto};
    }
}
