package com.speechpeach.speech.videoScript.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.member.entity.Member;
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

    @Column(name = "video_script_title", nullable = false)
    private String title;

    /*
    @TODO
    null이 허용되는 컬럼에 기본값 생각해서 추가 로직 구현
    */

    @Column(name = "video_script_highlight", columnDefinition = "TEXT", length = 500)
    private String highlight;

    @Column(name = "video_script_slash", columnDefinition = "TEXT", length = 500)
    private String slash;

    @Column(name = "video_script_color", columnDefinition = "TEXT", length = 500)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private VideoScript(String title, String highlight, String slash, String color, Member member, YoutubeVideo youtubeVideo) {
        this.title = title;
        this.highlight = highlight;
        this.slash = slash;
        this.color = color;
        this.member = member;
        this.youtubeVideo = youtubeVideo;
    }

    public static VideoScript of(String videoScriptTitle, String videoScriptHighlight, String videoScriptSlash, String videoScriptColor, Member member, YoutubeVideo youtubeVideo){
        return new VideoScript(videoScriptTitle, videoScriptHighlight, videoScriptSlash, videoScriptColor, member, youtubeVideo);
    }
}
