package com.mrurespect.onlineteachingplatform.entity;

import jakarta.persistence.*;

import java.util.Date;

@Table
@Entity(name = "assotiation_table")
public class AssotiationTable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "entityA_id")
    private EntityA entityA;
    @ManyToOne
    @JoinColumn(name = "entityB_id")
    private EntityB entityB;
    @Column
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntityA getEntityA() {
        return entityA;
    }

    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }

    public EntityB getEntityB() {
        return entityB;
    }

    public void setEntityB(EntityB entityB) {
        this.entityB = entityB;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
