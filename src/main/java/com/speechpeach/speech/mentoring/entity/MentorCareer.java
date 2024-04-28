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
@Table(name = "mentor_careers")
public class MentorCareer extends BaseEntity {

    @Id
    @Column(name = "mentor_career_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorCareerId;

    @Column(name = "mentor_career_content", columnDefinition = "TEXT", length = 500, nullable = false)
    private String mentorCareerContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    private MentorCareer(String mentorCareerContent, Mentor mentor){
        this.mentorCareerContent = mentorCareerContent;
        this.mentor = mentor;
    }

    public static MentorCareer createMentoCareer(String mentorCareerContent, Mentor mentor){
        return new MentorCareer(mentorCareerContent, mentor);
    }

}
