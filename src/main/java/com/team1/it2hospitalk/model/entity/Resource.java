package com.team1.it2hospitalk.model.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "it2_resource")
public class Resource {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private String resourceName;

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}
