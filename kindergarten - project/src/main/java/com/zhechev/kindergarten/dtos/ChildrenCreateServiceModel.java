package com.zhechev.kindergarten.dtos;

import com.zhechev.kindergarten.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenCreateServiceModel {
    private String name;
    private Gender gender;
}
