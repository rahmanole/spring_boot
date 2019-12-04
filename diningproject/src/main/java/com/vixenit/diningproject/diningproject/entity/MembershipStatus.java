package com.vixenit.diningproject.diningproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "membership_status")
@Getter
@Setter
public class MembershipStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String status = "applied";
    private boolean depositPaid;
    @Column(length = 5)
    private String mealStatus;

}
