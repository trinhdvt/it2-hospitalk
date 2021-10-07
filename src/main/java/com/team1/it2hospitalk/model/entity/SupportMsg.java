package com.team1.it2hospitalk.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "support_message")
public class SupportMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private Channel channel;

    private String title;

    private String body;

    @Column(name = "expect_qtt")
    private int expectQuantity;

    @Column(name = "current_qtt")
    private int currentQuantity;

    private Date createdAt;

    private Date closedAt;

    @Override
    public String toString() {
        return "SupportMsg{" +
                "id=" + id +
                ", creator=" + creator.getUsername() +
                ", channel=" + channel.getId() +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", expectedQuantity=" + expectQuantity +
                ", currentQuantity=" + currentQuantity +
                ", createdAt=" + createdAt +
                ", closedAt=" + closedAt +
                '}';
    }
}
