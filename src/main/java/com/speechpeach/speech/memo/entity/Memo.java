package com.speechpeach.speech.memo.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.user.entity.Member;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
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
    private String memoTitle;

    @Column(name = "memo_content",columnDefinition = "TEXT", length = 500, nullable = false)
    private String memoContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id", nullable = false)
    private YoutubeVideo youtubeVideo;

    private Memo(String memoTitle, String memoContent, Member member, YoutubeVideo youtubeVideo){
        this.memoTitle = memoTitle;
        this.memoContent = memoContent;
        this.member = member;
        this.youtubeVideo = youtubeVideo;
    }

    public static Memo createMemo(String memoTitle, String memoContent, Member member, YoutubeVideo youtubeVideo){
        return new Memo(memoTitle, memoContent, member, youtubeVideo);
    }
}
