package com.zhechev.kindergarten.dtos;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoAutoStart
public class CommentCreateModel {
    private String name;
    private String email;
    private String text;
}
