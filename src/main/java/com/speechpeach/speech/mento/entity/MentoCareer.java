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
@Table(name = "mento_careers")
public class MentoCareer extends BaseEntity {

    @Id
    @Column(name = "mento_career_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoCareerId;

    @Column(name = "mento_career_content", columnDefinition = "TEXT", length = 500, nullable = false)
    private String mentoCareerContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mento_id", nullable = false)
    private Mento mento;

    @Builder
    public MentoCareer(String mentoCareerContent, Mento mento){
        this.mentoCareerContent = mentoCareerContent;
        this.mento = mento;
    }

}
