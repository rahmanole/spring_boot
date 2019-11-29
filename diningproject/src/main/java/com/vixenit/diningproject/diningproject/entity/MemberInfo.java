/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixenit.diningproject.diningproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "member_info")
@Getter
@Setter
@NoArgsConstructor
public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30,nullable = false)
    private String name;
    @Column(name="mobile",length = 15,nullable = false,unique = true)
    private String mobile;
    @Column(name="email",length = 30,nullable = false,unique = true)
    private String email;
    @Column(name="dept_name",length = 30,nullable = false)
    private String deptName;
    @Column(name="reg_num",length = 30,nullable = false,unique = true)
    private String regNum;
    @Column(name="session",length = 10,nullable = false)
    private String session;
    @Column(name="application_status",length = 10,nullable = false)
    private String applicationStatus = "applied";
    @Column(name="meal_status",length = 10)
    private String mealStatus;
    private Date applicationDate;
    @Column(name="card_no",length =10,unique = true)
    private String cardNo;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Image> images;
}
