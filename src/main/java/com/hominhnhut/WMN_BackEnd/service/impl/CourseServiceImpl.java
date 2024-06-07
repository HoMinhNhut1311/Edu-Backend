package com.hominhnhut.WMN_BackEnd.service.impl;

import com.hominhnhut.WMN_BackEnd.domain.enity.Category;
import com.hominhnhut.WMN_BackEnd.domain.enity.Course;
import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.request.CourseDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.CourseDtoResponse;
import com.hominhnhut.WMN_BackEnd.domain.response.EpisodeDtoResponse;
import com.hominhnhut.WMN_BackEnd.exception.errorType;
import com.hominhnhut.WMN_BackEnd.exception.myException.AppException;
import com.hominhnhut.WMN_BackEnd.mapper.impl.CourseMapper;
import com.hominhnhut.WMN_BackEnd.repository.CategoryRepository;
import com.hominhnhut.WMN_BackEnd.repository.CourseRepository;
import com.hominhnhut.WMN_BackEnd.service.Interface.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;

    CourseMapper courseMapper;

    CategoryRepository categoryRepository;



    @Override
    public CourseDtoResponse saveCourse(CourseDtoRequest request) {
        Course course = courseMapper.mapFromRequest(request);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        course.setCategory(category);
        return courseMapper.mapToResponese(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        courseRepository.delete(course);
    }

    @Override
    public CourseDtoResponse updateCourse(CourseDtoRequest request,String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        request.setCourseId(courseId);
        Course courseUpdate = courseRepository.save
                (courseMapper.mapFromRequest(request));
        return courseMapper.mapToResponese(courseUpdate);
    }

    @Override
    public Set<CourseDtoResponse> getAllCourse() {
        List<CourseDtoResponse> courseDtoResponses = courseRepository.findAll().stream()
                .map(courseMapper::mapToResponese).toList();
        return new HashSet<>(courseDtoResponses);
    }

    @Override
    public CourseDtoResponse getCourseById(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        return courseMapper.mapToResponese(course);
    }

    @Override
    public Set<CourseDtoResponse> getCourseByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        List<CourseDtoResponse> responses = category.getCourses().stream().map(courseMapper::mapToResponese).toList();
        return new HashSet<>(responses);
    }

}
