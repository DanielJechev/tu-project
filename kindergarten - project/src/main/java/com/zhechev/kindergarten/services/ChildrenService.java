package com.zhechev.kindergarten.services;

import com.zhechev.kindergarten.dtos.ChildrenCreateServiceModel;

import java.util.List;

public interface ChildrenService {
    boolean areThereChildren();

    List<ChildrenCreateServiceModel> getChild(String childrenName);
}
