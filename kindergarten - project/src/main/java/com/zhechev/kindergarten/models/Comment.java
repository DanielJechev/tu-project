package com.zhechev.kindergarten.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity {
    private String name;
    private String email;
    private String text;

    private Integer user_id;
}
