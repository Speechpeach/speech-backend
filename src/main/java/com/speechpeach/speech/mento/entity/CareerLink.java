package com.speechpeach.speech.mento.entity;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
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
    @JoinColumn(name = "mento_id", nullable = false)
    private Mento mento;

    @Builder
    public CareerLink(String careerLinkContent, Mento mento){
        this.careerLinkContent = careerLinkContent;
        this.mento = mento;
    }
}
