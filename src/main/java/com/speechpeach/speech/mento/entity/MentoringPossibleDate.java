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
import jakarta.persistence.ManyToOne;
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
@Table(name = "mentoring_possible_dates")
public class MentoringPossibleDate extends BaseEntity {

    @Id
    @Column(name = "mentoring_possible_date_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoringPossibleDateId;

    @Column(name = "mentoring_possible_date_day", nullable = false)
    private String mentoringPossibleDateDay;

    @Column(name = "mentoring_possible_date_time_id", nullable = false)
    private String mentoringPossibleDateTimeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mento_id", nullable = false)
    private Mento mentoId;
}
