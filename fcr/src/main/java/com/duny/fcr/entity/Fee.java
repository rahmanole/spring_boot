package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="fee")
@Getter
@Setter
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="fee_type")
    private String feeType;

}
