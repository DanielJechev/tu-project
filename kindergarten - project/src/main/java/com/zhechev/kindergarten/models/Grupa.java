package com.zhechev.kindergarten.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Grupa extends BaseEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private Vid vid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
