package com.persist.chartable.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "podcaster")

public class PodNovaSignUpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POD_NOVA_ID")
    public Long podNovaId;

    // @Version
    // private Long version;

    @Column(name = "NAME")
    public String name;

    @Column(name = "EMAIL_ID")
    public String email;

    @Column(name = "PASSWORD")
    public String password;

}
