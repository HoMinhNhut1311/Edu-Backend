package com.hominhnhut.WMN_BackEnd.service.Interface;

import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.request.EpisodeDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.EpisodeDtoResponse;

import java.util.List;
import java.util.Set;

public interface EpisodeService {

    EpisodeDtoResponse saveEpisode(EpisodeDtoRequest request);

    void deleteEpisode(String episdeId);
    EpisodeDtoResponse updateEpisode(EpisodeDtoRequest request);

    Set<EpisodeDtoResponse> getAllEpisode();

    EpisodeDtoResponse getEpisodeById(String episodeId);

    List<EpisodeDtoResponse> getAllEpisodeByCourseIdAndOrderByOrderAsc(String courseId);
}
