package com.speechpeach.speech.memo.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.member.entity.Member;
import com.speechpeach.speech.youtubeVideo.entity.YoutubeVideo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "memos")
public class Memo extends BaseEntity {

    @Id
    @Column(name = "memo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @Column(name = "memo_title", nullable = false)
    private String title;

    @Column(name = "memo_content",columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id", nullable = false)
    private YoutubeVideo youtubeVideo;

    private Memo(String title, String content, Member member, YoutubeVideo youtubeVideo){
        this.title = title;
        this.content = content;
        this.member = member;
        this.youtubeVideo = youtubeVideo;
    }

    public static Memo of(String title, String content, Member member, YoutubeVideo youtubeVideo){
        return new Memo(title, content, member, youtubeVideo);
    }
}
