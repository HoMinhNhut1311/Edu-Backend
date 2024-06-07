package com.hominhnhut.WMN_BackEnd.domain.enity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String courseId;

    String courseTitle;

    String courseDes;

    long coursePrice;

    @OneToOne
    @JoinColumn(name = "mediaFileId")
    MediaFile banner;

    @ManyToOne
    @JoinColumn(name = "userId")
    // Giáo viên
    User teacher;

    // Danh sách các tập
    @OneToMany(cascade = CascadeType.ALL)
    Set<Episode> episode;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    Category category;



}
