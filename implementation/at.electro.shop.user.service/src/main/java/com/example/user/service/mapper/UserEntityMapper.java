package com.example.user.service.mapper;
import com.example.user.service.api.model.user.UserDto;
import com.example.user.service.usercart.model.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(uses={ProductMapper.class, CartMapper.class})
public interface UserEntityMapper {
    UserEntity toDao(UserDto userDto);
    UserDto toApi(UserEntity userEntity);
}


