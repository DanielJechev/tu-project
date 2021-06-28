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
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Children extends BaseEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
