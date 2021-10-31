package com.team1.it2hospitalk.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private Date createdAt;

    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @OneToMany(mappedBy = "workHospital")
    private List<User> staff;

    @PrePersist
    void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", manager=" + manager.getFullName() +
                '}';
    }
}
