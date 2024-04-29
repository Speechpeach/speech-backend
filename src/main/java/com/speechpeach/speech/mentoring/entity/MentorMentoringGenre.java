package com.speechpeach.speech.mentoring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "mentor_mentoring_genre")
public class MentorMentoringGenre {

    @Id
    @Column(name = "mentoring_genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private MentoringGenre mentoringGenre;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    private MentorMentoringGenre(MentoringGenre mentoringGenre, Mentor mentor){
        this.mentoringGenre = mentoringGenre;
        this.mentor = mentor;
    }

    public MentorMentoringGenre of(MentoringGenre mentoringGenre, Mentor mentor){
        return new MentorMentoringGenre(mentoringGenre, mentor);
    }
}
