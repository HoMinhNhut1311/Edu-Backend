package com.hominhnhut.WMN_BackEnd.mapper.impl;

import com.hominhnhut.WMN_BackEnd.domain.EnumRole;
import com.hominhnhut.WMN_BackEnd.domain.enity.Course;
import com.hominhnhut.WMN_BackEnd.domain.enity.User;
import com.hominhnhut.WMN_BackEnd.domain.request.CourseDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.CourseDtoResponse;
import com.hominhnhut.WMN_BackEnd.exception.errorType;
import com.hominhnhut.WMN_BackEnd.exception.myException.AppException;
import com.hominhnhut.WMN_BackEnd.mapper.MyMapperInterFace;
import com.hominhnhut.WMN_BackEnd.repository.CategoryRepository;
import com.hominhnhut.WMN_BackEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper implements
        MyMapperInterFace<CourseDtoRequest, Course, CourseDtoResponse> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Course mapFromRequest(CourseDtoRequest R) {
        User user = userRepository.findById(R.getTeacherId()).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        if (user.getRoles().contains(EnumRole.Teacher)) {
            throw new AppException(errorType.RoleNameIsNotTeacher);
        }

        categoryRepository.findById(R.getCategoryId()).orElseThrow(
                () -> new AppException(errorType.CategoryIdIsNotExist)
        );
        Course course = modelMapper.map(R,Course.class);
        course.setTeacher(user);
        return course;
    }

    @Override
    public CourseDtoResponse mapToResponese(Course course) {
        // Kiểm tra xem TypeMap đã tồn tại hay chưa
        TypeMap<Course, CourseDtoResponse> responseTypeMap = modelMapper.getTypeMap(Course.class, CourseDtoResponse.class);
        if (responseTypeMap == null) {
            // Nếu TypeMap chưa tồn tại, tạo mới và thêm mapping
            responseTypeMap = modelMapper.createTypeMap(Course.class, CourseDtoResponse.class);
            responseTypeMap.addMapping((src) -> src.getTeacher().getUserProfile().getProfileFullName(),
                    CourseDtoResponse::setTeacherName);
            responseTypeMap.addMapping((src) -> src.getTeacher().getUserId(),
                    CourseDtoResponse::setTeacherId);
        }

        return modelMapper.map(course, CourseDtoResponse.class);
    }

    @Override
    public Course mapNewProvider(Course course, Course eUpdate) {
        return null;
    }
}
