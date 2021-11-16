package com.team1.it2hospitalk.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "it2_transfer_form")
public class TransferForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "send_hospital_id")
    private Hospital sendHospital;

    @ManyToOne
    @JoinColumn(name = "receive_hospital_id")
    private Hospital receiveHospital;

    @ManyToOne
    @JoinColumn(name = "send_user_id")
    private User sendUser;

    @Column(name = "user_confirmed")
    private boolean isUserConfirmed;

    @Column(name = "manager_accepted")
    private boolean isManagerAccepted;

    @Column(name = "closed")
    private boolean isClosed;

    private String title;

    private Date startTime;

    private Date endTime;

    private String patientInformation;

    private String medicalSummary;

    private String reason;

    @Override
    public String toString() {
        return "TransferForm{" +
                "id=" + id +
                ", sendHospital=" + sendHospital.getName() +
                ", receiveHospital=" + receiveHospital.getName() +
                ", sendUser=" + sendUser.getFullName() +
                ", isUserConfirmed=" + isUserConfirmed +
                ", isManagerAccepted=" + isManagerAccepted +
                ", isClosed=" + isClosed +
                ", title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reason='" + reason + '\'' +
                '}';
    }
}
