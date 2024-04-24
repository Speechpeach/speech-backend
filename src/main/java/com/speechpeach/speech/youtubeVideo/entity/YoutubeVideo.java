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

    @Column(nullable = false)
    private String youtubeVideoRequestId;

    @Column(nullable = false)
    private String youtubeVideoChannelTitle;

    @Column(nullable = false)
    private String youtubeVideoTitle;

    @Column(nullable = false)
    private LocalDateTime youtubeVideoPublishedAt;

    @OneToMany(mappedBy = "youtubeVideo", cascade = PERSIST, orphanRemoval = true)
    private List<YoutubeVideoLike> youtubeVideoLikes = new ArrayList<>();

    private YoutubeVideo(String youtubeVideoRequestId, String youtubeVideoChannelTitle, String youtubeVideoTitle, LocalDateTime youtubeVideoPublishedAt) {
        this.youtubeVideoRequestId = youtubeVideoRequestId;
        this.youtubeVideoChannelTitle = youtubeVideoChannelTitle;
        this.youtubeVideoTitle = youtubeVideoTitle;
        this.youtubeVideoPublishedAt = youtubeVideoPublishedAt;
    }

    public static YoutubeVideo createYoutubeVideo(String youtubeVideoRequestId, String youtubeVideoChannelTitle, String youtubeVideoTitle, LocalDateTime youtubeVideoPublishedAt) {
        return new YoutubeVideo(youtubeVideoRequestId, youtubeVideoChannelTitle, youtubeVideoTitle, youtubeVideoPublishedAt);
    }

}
