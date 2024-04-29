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
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_apply_id", nullable = false)
    private MentoringApplication mentoringApplication;

    @Column(name = "review_star",columnDefinition = "TINYINT(3)", nullable = false)
    private int reviewStar;

    @Column(name = "review_content", columnDefinition = "TEXT", length = 500, nullable = false)
    private String reviewContent;

    private Review(int reviewStar, String reviewContent){
        this.reviewStar = reviewStar;
        this.reviewContent = reviewContent;
    }

    public static Review createReview(int reviewStar, String reviewContent){
        return new Review(reviewStar, reviewContent);
    }
}
