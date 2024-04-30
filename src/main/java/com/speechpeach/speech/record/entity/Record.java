package com.speechpeach.speech.record.entity;


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
@Table(name = "records")
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(name = "record_title", nullable = false)
    private String title;

    @Column(name = "record_content_url", nullable = false)
    private String contentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private Record(String title, String contentUrl, Member member, YoutubeVideo youtubeVideo) {
        this.title = title;
        this.contentUrl = contentUrl;
        this.member = member;
        this.youtubeVideo = youtubeVideo;
    }

    public static Record of(String recordTitle, String recordContentUrl, Member member, YoutubeVideo youtubeVideo) {
        return new Record(recordTitle, recordContentUrl, member, youtubeVideo);
    }
}
