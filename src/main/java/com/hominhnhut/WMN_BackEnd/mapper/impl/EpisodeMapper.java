package com.hominhnhut.WMN_BackEnd.mapper.impl;

import com.hominhnhut.WMN_BackEnd.domain.enity.Course;
import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.request.EpisodeDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.EpisodeDtoResponse;
import com.hominhnhut.WMN_BackEnd.exception.errorType;
import com.hominhnhut.WMN_BackEnd.exception.myException.AppException;
import com.hominhnhut.WMN_BackEnd.mapper.MyMapperInterFace;
import com.hominhnhut.WMN_BackEnd.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EpisodeMapper implements
        MyMapperInterFace<EpisodeDtoRequest, Episode, EpisodeDtoResponse> {

    CourseRepository courseRepository;

    ModelMapper modelMapper;

    @Override
    public Episode mapFromRequest(EpisodeDtoRequest R) {
        Course course = courseRepository.findById(R.getCourseId()).orElseThrow(
                () -> new AppException(errorType.CourseIdNotExist)
         );
        Episode episode = modelMapper.map(R, Episode.class);
        episode.setCourse(course);
        return episode;
    }

    @Override
    public EpisodeDtoResponse mapToResponese(Episode episode) {
        return modelMapper.map(episode, EpisodeDtoResponse.class);
    }

    @Override
    public Episode mapNewProvider(Episode episode, Episode eUpdate) {
        return null;
    }
}
