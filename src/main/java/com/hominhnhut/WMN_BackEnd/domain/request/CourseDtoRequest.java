package com.hominhnhut.WMN_BackEnd.domain.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDtoRequest {

    String courseId = "";
    String courseTitle;

    String courseDes;

    long coursePrice;

    String teacherId;

    Integer categoryId;


}
