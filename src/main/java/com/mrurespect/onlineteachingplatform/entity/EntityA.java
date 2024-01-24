package com.mrurespect.onlineteachingplatform.entity;

import jakarta.persistence.*;

import java.util.Set;

@Table
@Entity
public class EntityA {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assotiation_table",
                joinColumns = @JoinColumn(name = "entityA_id"),
                inverseJoinColumns = @JoinColumn(name = "entityB_id"))
    Set<EntityB> entityBS ;
}
