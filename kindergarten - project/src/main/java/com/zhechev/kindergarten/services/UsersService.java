package com.zhechev.kindergarten.services;

import com.zhechev.kindergarten.dtos.ChildrenCreateServiceModel;
import com.zhechev.kindergarten.dtos.CommentCreateServiceModel;
import com.zhechev.kindergarten.dtos.GroupServiceModel;
import com.zhechev.kindergarten.dtos.SubjectServiceModel;

public interface UsersService {
    void createChildrenForUser(String username, ChildrenCreateServiceModel hero) throws Exception;

    void chooseGroupForChildren(String username, GroupServiceModel serviceModel) throws Exception;

    void chooseZipForChildren(String username, SubjectServiceModel serviceModel) throws Exception;

    void createUserComment(String username, CommentCreateServiceModel serviceModel) throws Exception;
}
