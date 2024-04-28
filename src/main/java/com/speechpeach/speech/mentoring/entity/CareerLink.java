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
@Table(name = "career_links")
public class CareerLink extends BaseEntity {

    @Id
    @Column(name = "career_link_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long careerLinkId;

    @Column(name ="career_link_content", nullable = false)
    private String careerLinkContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    private CareerLink(String careerLinkContent, Mentor mentor){
        this.careerLinkContent = careerLinkContent;
        this.mentor = mentor;
    }

    public static CareerLink createCareerLink (String careerLinkContent, Mentor mentor){
        return new CareerLink(careerLinkContent, mentor);
    }
}
