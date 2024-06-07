package com.hominhnhut.WMN_BackEnd.service.impl;

import com.hominhnhut.WMN_BackEnd.domain.enity.Episode;
import com.hominhnhut.WMN_BackEnd.domain.enity.MediaFile;
import com.hominhnhut.WMN_BackEnd.domain.enity.UserProfile;
import com.hominhnhut.WMN_BackEnd.exception.errorType;
import com.hominhnhut.WMN_BackEnd.exception.myException.AppException;
import com.hominhnhut.WMN_BackEnd.repository.EpisodeRepository;
import com.hominhnhut.WMN_BackEnd.repository.MediaFileRepository;
import com.hominhnhut.WMN_BackEnd.repository.UserProfileRepository;
import com.hominhnhut.WMN_BackEnd.service.Interface.CloudinaryService;
import com.hominhnhut.WMN_BackEnd.service.Interface.MediaFileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MediaFileServiceImpl implements MediaFileService {

    MediaFileRepository mediaFileRepository;
    EpisodeRepository episodeRepository;
    UserProfileRepository userProfileRepository;
    CloudinaryService cloudinaryService;




    public MediaFile uploadFileToProfile(MultipartFile file, String profileId) {
        UserProfile userProfile = this.userProfileRepository.findById(profileId).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        Map result = this.cloudinaryService.uploadImage(file);

        MediaFile fileExist = userProfile.getMediaFile();
        System.out.println(fileExist);
        // Kiểm tra File đã tồn tại hay chưa
        if (fileExist != null) {
            // Xóa trên Cloudinary
            try {
                this.cloudinaryService.delete(fileExist.getMediaFileID());
                System.out.println("đã xóa trên Cloundinary");
                userProfile.setMediaFile(null);
                this.mediaFileRepository.deleteById(fileExist.getMediaFileID());
                System.out.println("đã xóa trên Database");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        MediaFile mediaFile = new MediaFile();
        mediaFile.setMediaFileID(result.get("public_id").toString());
        mediaFile.setMediaFilePath(result.get("url").toString());
        mediaFile.setMediaFileName(file.getOriginalFilename());
        mediaFile.setMediaFileType(result.get("resource_type").toString());
        mediaFile.setCreateAt(new Date());

        this.mediaFileRepository.saveAndFlush(mediaFile);

        userProfile.setMediaFile(mediaFile);
        this.userProfileRepository.save(userProfile);
        return mediaFile;
    }

    @Override
    public MediaFile uploadVideoToEpisode(MultipartFile file, String episodeId) {
        Episode episode = episodeRepository.findById(episodeId).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
        // Tìm thấy Episode tồn tại
        Map result = cloudinaryService.uploadVideo(file);
        // Tạo ra MediaFile từ Response mà Cloudinary trả về
        MediaFile mediaFile = new MediaFile();
        mediaFile.setMediaFileID(result.get("public_id").toString());
        mediaFile.setMediaFilePath(result.get("url").toString());
        mediaFile.setMediaFileName(file.getOriginalFilename());
        mediaFile.setMediaFileType(result.get("resource_type").toString());
        mediaFile.setCreateAt(new Date());

        // Thêm Media vào csdl
        mediaFile =  mediaFileRepository.saveAndFlush(mediaFile);

        // Cập nhật MediaFile của Episode
        episode.setEpisodeFile(mediaFile);
        // Lưu
        episodeRepository.save(episode);
        return mediaFile;
    }

}
