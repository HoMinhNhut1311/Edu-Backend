package com.hominhnhut.WMN_BackEnd.domain.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDtoRequest {

    String episodeTitle;

    String episodeDes;

    Integer episodeOrder;

    Double duration;

    String courseId;

}
