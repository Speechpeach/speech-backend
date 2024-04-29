package com.speechpeach.speech.mentoring.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "mentor_history")
public class MentorHistory extends BaseEntity {

    @Id
    @Column(name = "mentor_history_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name ="mentor_history_content", nullable = false)
    private String historyContent;

    @Column(name = "mentor_history_link")
    private String historyLink;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    private MentorHistory(String historyContent, String historyLink, Mentor mentor){
        this.historyContent = historyContent;
        this.historyLink = historyLink;
        this.mentor = mentor;
    }

    public static MentorHistory of(String careerLinkContent, String historyLink, Mentor mentor){
        return new MentorHistory(careerLinkContent, historyLink, mentor);
    }
}
