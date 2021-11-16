package com.team1.it2hospitalk.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "it2_channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    private String title;

    private Date createdAt;

    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "it2_channel_user",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @OneToOne(mappedBy = "channel")
    private SupportMsg supportMsg;

    @PrePersist
    void prePersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", creator=" + creator.getUsername() +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
