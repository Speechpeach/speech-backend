package com.speechpeach.speech.mento.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import com.speechpeach.speech.user.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "mento_careers")
public class MentoCareer extends BaseEntity {

    @Id
    @Column(name = "mento_career_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoCareerId;

    @Column(name = "mento_career_content", nullable = false)
    private String mentoCareerContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mento_id", nullable = false)
    private Mento mentoId;

}
