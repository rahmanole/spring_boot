package com.example.thymeleaf.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotEmpty(message = "Please enter role name")
    private String roleName;

    public Role(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName.toUpperCase();
    }
}
