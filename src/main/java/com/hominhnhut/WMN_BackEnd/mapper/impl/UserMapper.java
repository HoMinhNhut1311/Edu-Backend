package com.hominhnhut.WMN_BackEnd.mapper.impl;

import com.hominhnhut.WMN_BackEnd.domain.enity.Role;
import com.hominhnhut.WMN_BackEnd.domain.enity.User;
import com.hominhnhut.WMN_BackEnd.domain.request.UserDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.UserDtoResponse;
import com.hominhnhut.WMN_BackEnd.mapper.MyMapperInterFace;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserMapper implements
        MyMapperInterFace<UserDtoRequest,User, UserDtoResponse> {

    ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Respone
        TypeMap<User,UserDtoResponse> mapResponse =
                this.modelMapper.createTypeMap(User.class,UserDtoResponse.class);
        mapResponse.addMapping(User::getUsername,UserDtoResponse::setUserName);
        mapResponse.addMapping(src -> src.getUserProfile().getProfileFullName(), UserDtoResponse::setFullName);
        mapResponse.addMapping(src -> src.getUserProfile().getProfileEmail(), UserDtoResponse::setEmail);
        mapResponse.addMapping(src -> src.getUserProfile().isProfileSex(), UserDtoResponse::setSex);
        mapResponse.addMapping(src -> src.getUserProfile().getProfilePhone(), UserDtoResponse::setPhone);
        mapResponse.addMapping(User::getUserId, UserDtoResponse::setUserId);
        mapResponse.addMapping(src -> src.getUserProfile().getProfileId(), UserDtoResponse::setProfileId);
        mapResponse.addMapping(src -> src.getUserProfile().getProfileBirth(), UserDtoResponse::setBirth);
        mapResponse.addMapping(src -> src.getUserProfile().getMediaFile(), UserDtoResponse::setMediaFile);


    }

    @Override
    public User mapFromRequest(UserDtoRequest R) {
        return this.modelMapper.map(R,User.class);
    }

    @Override
    public UserDtoResponse mapToResponese(User user) {
        UserDtoResponse userDtoResponse = this.modelMapper.map(user,UserDtoResponse.class);
        if (user.getRoles() != null) {
            List<String> roleName = user.getRoles().stream().map(Role::getRoleName).toList();
            userDtoResponse.setRoleNames(new HashSet<>(roleName));
        }

        return userDtoResponse;

    }

    @Override
    public User mapNewProvider(User user,User userUpdate) {
        TypeMap<User,User> map =
                this.modelMapper.createTypeMap(User.class,User.class);
        Provider<User> userProvider = new Provider<User>() {
            @Override
            public User get(ProvisionRequest<User> provisionRequest) {
                return user;
            }
        };

        map.setProvider(userProvider);
        return this.modelMapper.map(userUpdate,User.class);
    }
}