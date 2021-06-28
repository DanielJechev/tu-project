package com.zhechev.kindergarten.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;

    @NotNull
    private String egn;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Children children;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Grupa grupa;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Subject subject;
}
