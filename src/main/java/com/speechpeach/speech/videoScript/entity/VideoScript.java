package com.speechpeach.speech.videoScript.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.user.entity.User;
import com.speechpeach.speech.youtubeVideo.entity.YoutubeVideo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video_scripts")
public class VideoScript extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoScriptId;

    @Column(nullable = false)
    private String videoScriptTitle;

    /*
    @TODO
    null이 허용되는 컬럼에 기본값 생각해서 추가 로직 구현
    */

    @Column(columnDefinition = "TEXT", length = 500)
    private String videoScriptHighlight;

    @Column(columnDefinition = "TEXT", length = 500)
    private String videoScriptSlash;

    @Column(columnDefinition = "TEXT", length = 500)
    private String videoScriptColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private VideoScript(String videoScriptTitle, String videoScriptHighlight, String videoScriptSlash, String videoScriptColor, User user, YoutubeVideo youtubeVideo) {
        this.videoScriptTitle = videoScriptTitle;
        this.videoScriptHighlight = videoScriptHighlight;
        this.videoScriptSlash = videoScriptSlash;
        this.videoScriptColor = videoScriptColor;
        this.user = user;
        this.youtubeVideo = youtubeVideo;
    }

    public static VideoScript createVideoScript(String videoScriptTitle, String videoScriptHighlight, String videoScriptSlash, String videoScriptColor, User user, YoutubeVideo youtubeVideo){
        return new VideoScript(videoScriptTitle, videoScriptHighlight, videoScriptSlash, videoScriptColor, user, youtubeVideo);
    }
}
