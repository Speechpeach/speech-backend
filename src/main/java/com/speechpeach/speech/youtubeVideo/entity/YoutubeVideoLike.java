package com.speechpeach.speech.youtubeVideo.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.member.entity.Member;
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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private YoutubeVideoLike(Member member, YoutubeVideo youtubeVideo){
        this.member = member;
        this.youtubeVideo = youtubeVideo;
    }

    public static YoutubeVideoLike of(Member member, YoutubeVideo youtubeVideo){
        return new YoutubeVideoLike(member, youtubeVideo);
    }
}
