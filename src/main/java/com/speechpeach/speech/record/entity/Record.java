package com.speechpeach.speech.record.entity;


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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private Record(String title, String contentUrl, User user, YoutubeVideo youtubeVideo) {
        this.title = title;
        this.contentUrl = contentUrl;
        this.user = user;
        this.youtubeVideo = youtubeVideo;
    }

    public static Record of(String recordTitle, String recordContentUrl, User user, YoutubeVideo youtubeVideo) {
        return new Record(recordTitle, recordContentUrl, user, youtubeVideo);
    }
}
