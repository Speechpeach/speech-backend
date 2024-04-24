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

    @Column(nullable = false)
    private String recordTitle;

    @Column(nullable = false)
    private String recordContentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youtube_video_id")
    private YoutubeVideo youtubeVideo;

    private Record(String recordTitle, String recordContentUrl, User user, YoutubeVideo youtubeVideo) {
        this.recordTitle = recordTitle;
        this.recordContentUrl = recordContentUrl;
        this.user = user;
        this.youtubeVideo = youtubeVideo;
    }

    public static Record createRecord(String recordTitle, String recordContentUrl, User user, YoutubeVideo youtubeVideo) {
        return new Record(recordTitle, recordContentUrl, user, youtubeVideo);
    }
}
