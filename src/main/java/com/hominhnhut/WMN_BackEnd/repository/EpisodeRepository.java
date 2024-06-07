package com.hominhnhut.WMN_BackEnd.repository;

import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface EpisodeRepository extends JpaRepository<Episode, String> {

    @Query(value = "SELECT e.* FROM epsisodes e LEFT JOIN courses c ON e.course_id = c.course_id\n" +
            "WHERE c.course_id = :courseId ORDER BY e.episode_order ASC ",nativeQuery = true)
    Set<Episode> findAllEpisodeByCourseIdAndOrderByOrderAsc(@Param("courseId") String courseId);

}
