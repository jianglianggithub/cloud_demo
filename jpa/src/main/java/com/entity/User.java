package com.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Table(name = "t_user")
@Entity

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "pass_word", nullable = false, length = 64)
    private String password;


    @ManyToOne()
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private Dept dept;

    /* mappedBy 代表被控放 那么 被控方 做操作的时候 是不会触发级联更新的？ */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
    List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    /* 如果指定了对方表的 field 不是 primay key 那么得在插入时候先定义好 外键关联 主键的 id值  */
            Crad crad;

    public Crad getCrad() {
        return crad;
    }

    public void setCrad(Crad crad) {
        this.crad = crad;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dept=" + dept +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}