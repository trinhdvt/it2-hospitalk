package com.team1.it2hospitalk.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    private String body;

    private Date createdAt;

    @PrePersist
    void prePersist() {
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", creator=" + creator.getUsername() +
                ", channel=" + channel.getTitle() +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
