package com.speechpeach.speech.mentoring.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private MentoringApplication mentoringApplicationId;

    @Column(name = "review_star",columnDefinition = "TINYINT(3)", nullable = false)
    private int star;

    @Column(name = "review_content", columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    private Review(int star, String content){
        this.star = star;
        this.content = content;
    }

    public static Review createReview(int star, String content){
        return new Review(star, content);
    }
}
