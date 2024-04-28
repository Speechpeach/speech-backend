package com.speechpeach.speech.mentoring.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.member.entity.Member;
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
@Table(name = "mentors")
public class Mentor extends BaseEntity {

    @Id
    @Column(name = "mentor_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorId;

    @Column(name = "mentor_name", nullable = false)
    private String mentorName;

    @Column(name = "mentor_career_period", nullable = false)
    private String mentorCareerPeriod;

    @Column(name = "mentor_profile_image", nullable = false)
    private String mentorProfileImage;

    @Column(name = "mentor_genre", nullable = false)
    private String mentorGenre;

    @Column(name = "mentor_email", nullable = false)
    private String mentorEmail;

    @Column(name = "mentor_phone_number", nullable = false)
    private String mentorPhoneNumber;

    @Column(name = "mentor_title", nullable = false)
    private String mentorTitle;

    @Column(name = "mentor_content",columnDefinition = "TEXT", length = 500, nullable = false)
    private String mentorContent;

    @Column(name = "mentor_mentoring_method", nullable = false)
    private String mentorMentoringMethod;

    @Column(name = "mentor_mentoring_time", nullable = false)
    private int mentorMentoringTime;

    @Column(name = "mentor_mentoring_price", nullable = false)
    private int mentorMentoringPrice;

    @Column(name = "mentor_status_id", nullable = false)
    private String mentorStatusId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Mentor(String mentorName, String mentorCareerPeriod, String mentorProfileImage, String mentorGenre,
            String mentorEmail, String mentorPhoneNumber, String mentorTitle, String mentorContent, String mentorMentoringMethod,
            int mentorMentoringTime, int mentorMentoringPrice, String mentorStatusId, Member member){
        this.mentorName = mentorName;
        this.mentorCareerPeriod = mentorCareerPeriod;
        this.mentorProfileImage = mentorProfileImage;
        this.mentorGenre = mentorGenre;
        this.mentorEmail = mentorEmail;
        this.mentorPhoneNumber = mentorPhoneNumber;
        this.mentorTitle = mentorTitle;
        this.mentorContent = mentorContent;
        this.mentorMentoringMethod = mentorMentoringMethod;
        this.mentorMentoringTime = mentorMentoringTime;
        this.mentorMentoringPrice = mentorMentoringPrice;
        this.mentorStatusId = mentorStatusId;
        this.member = member;
    }

    public static Mentor createMento(String mentorName, String mentorCareerPeriod, String mentorProfileImage, String mentorGenre,
            String mentorEmail, String mentorPhoneNumber, String mentorTitle, String mentorContent, String mentorMentoringMethod,
            int mentorMentoringTime, int mentorMentoringPrice, String mentorStatusId, Member member){
        return new Mentor(mentorName, mentorCareerPeriod, mentorProfileImage, mentorGenre, mentorEmail, mentorPhoneNumber,
                mentorTitle, mentorContent, mentorMentoringMethod, mentorMentoringTime, mentorMentoringPrice,
                mentorStatusId, member);
    }

}
