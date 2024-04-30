package com.speechpeach.speech.youtubeVideo.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="likes")
public class YoutubeVideoLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private YoutubeVideoLike(User user, YoutubeVideo youtubeVideo){
        this.user = user;
        this.youtubeVideo = youtubeVideo;
    }

    public static YoutubeVideoLike of(User user, YoutubeVideo youtubeVideo){
        return new YoutubeVideoLike(user, youtubeVideo);
    }
}
