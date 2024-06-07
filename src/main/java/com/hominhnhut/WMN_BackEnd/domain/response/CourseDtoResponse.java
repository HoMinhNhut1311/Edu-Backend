package com.hominhnhut.WMN_BackEnd.domain.response;

import com.hominhnhut.WMN_BackEnd.domain.enity.Category;
import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.enity.MediaFile;
import com.hominhnhut.WMN_BackEnd.domain.enity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDtoResponse {

    String courseId;

    String courseTitle;

    String courseDes;

    long coursePrice;

    // Giáo viên
    String teacherId;

    String teacherName;

    Integer categoryId;

    MediaFile mediaFile;
}
