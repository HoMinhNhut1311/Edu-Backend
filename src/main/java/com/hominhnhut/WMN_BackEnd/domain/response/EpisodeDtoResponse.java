package com.hominhnhut.WMN_BackEnd.domain.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDtoResponse {

    String episodeId;

    String episodeTitle;

    String episodeDes;

    String episodeFilePath;

    Integer episodeOrder;

    Double duration;

    String courseId;

    LocalDate createAt;

}
