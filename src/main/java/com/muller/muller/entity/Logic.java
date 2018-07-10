package com.muller.muller.entity;

import javax.persistence.*;

@Entity
@Table(name = "logic")
public class Logic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String huellaOk;

    public String getHuellaOk() {
        return huellaOk;
    }

    public void setHuellaOk(String huellaOk) {
        this.huellaOk = huellaOk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
