package com.hominhnhut.WMN_BackEnd.restController;

import com.hominhnhut.WMN_BackEnd.domain.request.CourseDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.CourseDtoResponse;
import com.hominhnhut.WMN_BackEnd.service.Interface.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CourseController {

    CourseService courseService;


    @GetMapping
    public ResponseEntity<Set<CourseDtoResponse>> getAllCourse() {
        Set<CourseDtoResponse> responses = courseService.getAllCourse();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDtoResponse> getCourseById(
            @PathVariable("id") String courseId
    ) {
        CourseDtoResponse response = courseService.getCourseById(courseId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CourseDtoResponse> saveCourse(
            @RequestBody CourseDtoRequest request
            ) {
        CourseDtoResponse response = courseService.saveCourse(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDtoResponse> updateCourse(
            @PathVariable("id") String courseId,
            @RequestBody CourseDtoRequest request
    ) {
        CourseDtoResponse response = courseService.updateCourse(request,courseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable("id") String courseId
    ) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Set<CourseDtoResponse>> getCourseByCategoryId(
            @PathVariable("id") Integer categoryId
    ) {
        Set<CourseDtoResponse> responses = courseService.getCourseByCategory(categoryId);
        return  ResponseEntity.ok(responses);
    }
}
