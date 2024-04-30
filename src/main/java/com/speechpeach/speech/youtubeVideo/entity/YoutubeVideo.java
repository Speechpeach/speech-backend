package com.speechpeach.speech.youtubeVideo.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "youtube_videos")
public class YoutubeVideo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long youtubeVideoId;

    @Column(name = "youtube_video_request_id", nullable = false)
    private String requestId;

    @Column(name = "youtube_video_channel_title", nullable = false)
    private String channelTitle;

    @Column(name = "youtube_video_title", nullable = false)
    private String title;

    @Column(name = "youtube_video_publishedat", nullable = false)
    private LocalDateTime publishedAt;

    @OneToMany(mappedBy = "youtubeVideo", cascade = PERSIST, orphanRemoval = true)
    private List<YoutubeVideoLike> youtubeVideoLikes = new ArrayList<>();

    private YoutubeVideo(String requestId, String channelTitle, String title, LocalDateTime publishedAt) {
        this.requestId = requestId;
        this.channelTitle = channelTitle;
        this.title = title;
        this.publishedAt = publishedAt;
    }

    public static YoutubeVideo of(String youtubeVideoRequestId, String youtubeVideoChannelTitle, String youtubeVideoTitle, LocalDateTime youtubeVideoPublishedAt) {
        return new YoutubeVideo(youtubeVideoRequestId, youtubeVideoChannelTitle, youtubeVideoTitle, youtubeVideoPublishedAt);
    }

}
