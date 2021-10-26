package com.team1.it2hospitalk.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String username;

    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private int age = 0;

    private String job;

    private String phoneNumber;

    private String address;

    private String avatarUrl;

    @Builder.Default
    private boolean isBlock = false;

    private Date createdAt;

    private Date updatedAt;

    @OneToOne(mappedBy = "manager")
    private Hospital manageHospital;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital workHospital;

    @OneToMany(mappedBy = "creator")
    @OrderBy("updatedAt DESC, createdAt DESC")
    private List<Channel> createdChannels = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Channel> channels = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<SupportMsg> supportMsgs = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                '}';
    }

    @PrePersist
    void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = new Date();
    }
}
