package com.mrurespect.onlineteachingplatform.entity;

import jakarta.persistence.*;

import java.util.Set;

@Table
@Entity
public class EntityB {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "entityBS")
    Set<EntityA> entityAS;
}
