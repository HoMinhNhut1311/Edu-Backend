package com.hominhnhut.WMN_BackEnd.service.impl;

import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.request.EpisodeDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.EpisodeDtoResponse;
import com.hominhnhut.WMN_BackEnd.mapper.MyMapperInterFace;
import com.hominhnhut.WMN_BackEnd.repository.EpisodeRepository;
import com.hominhnhut.WMN_BackEnd.service.Interface.EpisodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class EpisodeServiceImpl implements EpisodeService {

    EpisodeRepository episodeRepository;
    MyMapperInterFace<EpisodeDtoRequest, Episode, EpisodeDtoResponse> episodeMapper;

    @Override
    public EpisodeDtoResponse saveEpisode(EpisodeDtoRequest request) {
        Episode episode = episodeMapper.mapFromRequest(request);
        episode.setCreateAt(LocalDate.now());
        return episodeMapper.mapToResponese(episodeRepository.save(episode));
    }

    @Override
    public void deleteEpisode(String episdeId) {

    }

    @Override
    public EpisodeDtoResponse updateEpisode(EpisodeDtoRequest request) {
        return null;
    }

    @Override
    public Set<EpisodeDtoResponse> getAllEpisode() {
        List<EpisodeDtoResponse> responses = episodeRepository.findAll().stream().map(episodeMapper::mapToResponese).toList();
        return new HashSet<>(responses);
    }

    @Override
    public EpisodeDtoResponse getEpisodeById(String episodeId) {
        return null;
    }

    @Override
    public List<EpisodeDtoResponse> getAllEpisodeByCourseIdAndOrderByOrderAsc(String courseId) {
        List<EpisodeDtoResponse> episodes = episodeRepository.findAllEpisodeByCourseIdAndOrderByOrderAsc(courseId).stream().map(episodeMapper::mapToResponese).toList();
        return episodes;
    }
}
