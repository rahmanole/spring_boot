package com.vixenit.diningproject.diningproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="depts")
@Getter
@Setter
@NoArgsConstructor
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="dept_name",length = 50,nullable = false,unique = true)
    private String deptName;

    @Column(name="dept_id",nullable = false,unique = true)
    private int deptId;

}
