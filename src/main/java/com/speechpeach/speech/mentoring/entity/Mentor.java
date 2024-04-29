package com.speechpeach.speech.mentoring.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String name;

    @Column(name = "mentor_career_period", nullable = false)
    @Enumerated(EnumType.STRING)
    private MentorCareerPeriod careerPeriod;

    @Column(name = "mentor_profile_image", nullable = false)
    private String profileImage;

    @Column(name = "mentor_genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private MentoringGenre mentoringGenre;

    @Column(name = "mentor_email", nullable = false)
    private String email;

    @Column(name = "mentor_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "mentor_title", nullable = false)
    private String title;

    @Column(name = "mentor_content",columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @Column(name = "mentor_mentoring_method", nullable = false)
    private String mentoringMethod;

    @Column(name = "mentor_mentoring_time", nullable = false)
    private int mentoringTime;

    @Column(name = "mentor_mentoring_price", nullable = false)
    private int mentoringPrice;

    @Column(name = "mentor_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MentorStatus mentorStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Mentor(String name, MentorCareerPeriod careerPeriod, String profileImage, MentoringGenre mentoringGenre,
            String email, String phoneNumber, String title, String content, String mentoringMethod,
            int mentoringTime, int mentoringPrice, MentorStatus mentorStatus, Member member){
        this.name = name;
        this.careerPeriod = careerPeriod;
        this.profileImage = profileImage;
        this.mentoringGenre = mentoringGenre;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
        this.content = content;
        this.mentoringMethod = mentoringMethod;
        this.mentoringTime = mentoringTime;
        this.mentoringPrice = mentoringPrice;
        this.mentorStatus = mentorStatus;
        this.member = member;
    }

    public static Mentor createMento(String name, MentorCareerPeriod careerPeriod, String profileImage, MentoringGenre mentoringGenre,
            String email, String phoneNumber, String title, String content, String mentoringMethod,
            int mentoringTime, int mentoringPrice, MentorStatus mentorStatus, Member member){
        return new Mentor(name, careerPeriod, profileImage, mentoringGenre, email, phoneNumber,
                title, content, mentoringMethod, mentoringTime, mentoringPrice,
                mentorStatus, member);
    }

}
