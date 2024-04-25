package com.speechpeach.speech.mento.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.user.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "mentos")
public class Mento extends BaseEntity {

    @Id
    @Column(name = "mento_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoId;

    @Column(name = "mento_name", nullable = false)
    private String mentoName;

    @Column(name = "mento_career_period", nullable = false)
    private String mentoCareerPeriod;

    @Column(name = "mento_profile_image", nullable = false)
    private String mentoProfileImage;

    @Column(name = "mento_genre", nullable = false)
    private String mentoGenre;

    @Column(name = "mento_email", nullable = false)
    private String mentoEmail;

    @Column(name = "mento_phone_number", nullable = false)
    private String mentoPhoneNumber;

    @Column(name = "mento_title", nullable = false)
    private String mentoTitle;

    @Column(name = "mento_content", nullable = false)
    private String mentoContent;

    @Column(name = "mento_mentoring_method", nullable = false)
    private String mentoMentoringMethod;

    @Column(name = "mento_mentoring_time", nullable = false)
    private int mentoMentoringTime;

    @Column(name = "mento_mentoring_price", nullable = false)
    private int mentoMentoringPrice;

    @Column(name = "mento_status_id", nullable = false)
    private String mentoStatusId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberId;

}