package com.team1.it2hospitalk.model.entity;

import com.team1.it2hospitalk.event.NewMessageEvent;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "it2_message")
@EntityListeners(NewMessageEvent.class)
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