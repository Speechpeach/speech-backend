package com.speechpeach.speech.mentoring.entity;

import com.speechpeach.speech.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "mentoring_applications")
public class MentoringApplication {

    @Id
    @Column(name = "mentoring_apply_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoringApplyId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime mentoringApplyDate;

    @Column(name = "mentoring_apply_mentee_name", nullable = false)
    private String mentoringApplyMenteeName;

    @Column(name = "mentoring_apply_mentee_phone_number", nullable = false)
    private String mentoringApplyMenteePhoneNumber;

    @Column(name = "mentoring_apply_mentee_email", nullable = false)
    private String mentoringApplyMenteeEmail;

    @Column(name = "mentoring_apply_to_mentor_message", columnDefinition = "TEXT", length = 500, nullable = false)
    private String mentoringApplyToMentorMessage;

    @Column(name = "mentoring_apply_status", nullable = false)
    private MentoringApplyStatus mentoringApplyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    private MentoringApplication(String mentoringApplyMenteeName,
            String mentoringApplyMenteePhoneNumber,
            String mentoringApplyMenteeEmail, String mentoringApplyToMentorMessage,
            MentoringApplyStatus mentoringApplyStatus,
            Member member, Mentor mentor) {
        this.mentoringApplyMenteeName = mentoringApplyMenteeName;
        this.mentoringApplyMenteePhoneNumber = mentoringApplyMenteePhoneNumber;
        this.mentoringApplyMenteeEmail = mentoringApplyMenteeEmail;
        this.mentoringApplyToMentorMessage = mentoringApplyToMentorMessage;
        this.mentoringApplyStatus = mentoringApplyStatus;
        this.member = member;
        this.mentor = mentor;
    }

    public static MentoringApplication createMentoringApplication(String mentoringApplyMenteeName,
            String mentoringApplyMenteePhoneNumber,
            String mentoringApplyMenteeEmail, String mentoringApplyToMentorMessage,
            MentoringApplyStatus mentoringApplyStatus,
            Member member, Mentor mentor) {
        return new MentoringApplication(mentoringApplyMenteeName, mentoringApplyMenteePhoneNumber,
                mentoringApplyMenteeEmail,
                mentoringApplyToMentorMessage, mentoringApplyStatus, member, mentor);
    }

}
