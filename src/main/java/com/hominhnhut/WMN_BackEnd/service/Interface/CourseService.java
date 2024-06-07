package com.hominhnhut.WMN_BackEnd.service.Interface;

import com.hominhnhut.WMN_BackEnd.domain.request.CourseDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.CourseDtoResponse;
import com.hominhnhut.WMN_BackEnd.domain.response.EpisodeDtoResponse;

import java.util.Set;

public interface CourseService {

    CourseDtoResponse saveCourse(CourseDtoRequest request);

    void deleteCourse(String courseId);

    CourseDtoResponse updateCourse(CourseDtoRequest request,String courseID);

    Set<CourseDtoResponse> getAllCourse();

    CourseDtoResponse getCourseById(String courseId);

    Set<CourseDtoResponse> getCourseByCategory(Integer categoryId);

}
