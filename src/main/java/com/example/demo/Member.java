package com.example.demo;

import jakarta.persistence.*;
@Entity
@Table(name = "members")
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    protected Member() {
    }

    public Member(String username,String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public long getid() {
        return id;
    }

    public String getUsername() {
        return username;
    }


}
