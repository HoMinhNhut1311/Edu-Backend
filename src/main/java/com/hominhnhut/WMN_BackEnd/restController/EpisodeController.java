package com.hominhnhut.WMN_BackEnd.restController;

import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.request.EpisodeDtoRequest;
import com.hominhnhut.WMN_BackEnd.domain.response.EpisodeDtoResponse;
import com.hominhnhut.WMN_BackEnd.mapper.MyMapperInterFace;
import com.hominhnhut.WMN_BackEnd.mapper.impl.EpisodeMapper;
import com.hominhnhut.WMN_BackEnd.service.Interface.EpisodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/episode")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EpisodeController {

    EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<
            Set<EpisodeDtoResponse>> getAllEpisode() {
        Set<EpisodeDtoResponse> responses = episodeService.getAllEpisode();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpisodeDtoResponse> getEpisodeById(
            @PathVariable("id") String episodeId
    ) {
        EpisodeDtoResponse response = episodeService.getEpisodeById(episodeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EpisodeDtoResponse> saveEpisode(
            @RequestBody EpisodeDtoRequest request
    ) {
        EpisodeDtoResponse response = episodeService.saveEpisode(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public  ResponseEntity<
            List<EpisodeDtoResponse>> getAllEpisodeByCourseIdAndOrderByOrderAsc
            (
                    @PathVariable("courseId") String courseId
            ) {
        List<EpisodeDtoResponse> responses = episodeService.getAllEpisodeByCourseIdAndOrderByOrderAsc(courseId);
        return ResponseEntity.ok(responses);
    }


}
