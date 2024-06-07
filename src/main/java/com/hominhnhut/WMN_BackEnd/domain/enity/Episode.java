package com.hominhnhut.WMN_BackEnd.domain.enity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "epsisodes")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Episode {

    // Trong tập chứa Video khóa học

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String episodeId;

    @ManyToOne
    @JoinColumn(name = "courseId")
    Course course;

    String episodeTitle;

    String episodeDes;

    Double duration;

    @OneToOne
    MediaFile episodeFile;

    @Column(unique = true)
    // Video thứ x
    Integer episodeOrder;

    LocalDate createAt;

    LocalDate updateAt;
}
