package com.zhechev.kindergarten.services.impl;

import com.zhechev.kindergarten.dtos.ChildrenCreateServiceModel;
import com.zhechev.kindergarten.dtos.CommentCreateServiceModel;
import com.zhechev.kindergarten.dtos.GroupServiceModel;
import com.zhechev.kindergarten.dtos.SubjectServiceModel;
import com.zhechev.kindergarten.exceptions.GroupNotFoundException;
import com.zhechev.kindergarten.exceptions.KindergartenNotFoundException;
import com.zhechev.kindergarten.models.Children;
import com.zhechev.kindergarten.models.Comment;
import com.zhechev.kindergarten.models.Grupa;
import com.zhechev.kindergarten.models.Subject;
import com.zhechev.kindergarten.models.User;
import com.zhechev.kindergarten.repositories.CommentRepository;
import com.zhechev.kindergarten.repositories.GrupaRepository;
import com.zhechev.kindergarten.repositories.UsersRepository;
import com.zhechev.kindergarten.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final GrupaRepository grupaRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createChildrenForUser(String username, ChildrenCreateServiceModel childrenCreateServiceModel) throws Exception {
        User user = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getChildren() != null) {
            throw new Exception("User already has a children");
        }

        Children children = new Children();
        children.setName(childrenCreateServiceModel.getName());
        children.setGender(childrenCreateServiceModel.getGender());
        children.setUser(user);
        user.setChildren(children);
        usersRepository.save(user);
    }

    @Override
    public void chooseGroupForChildren(String username, GroupServiceModel serviceModel) throws Exception {
        User user = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getGrupa() != null) {
            throw new KindergartenNotFoundException("ВЕЧЕ ИМАТЕ ИЗБРАНА ГРУПА !");
        }

        Grupa grupa = new Grupa();

        long count = grupaRepository.count();
        if (count < 300) {
            grupa.setVid(serviceModel.getVid());
            grupa.setUser(user);
            user.setGrupa(grupa);
            usersRepository.save(user);
        } else {
            throw new Exception("За съжаление всички места са вече заети !");
        }
    }

    @Override
    public void chooseZipForChildren(String username, SubjectServiceModel serviceModel) throws Exception {
        User user = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getSubject() != null) {
            throw new KindergartenNotFoundException("ВЕЧЕ  ИЗБРАХТЕ ЗИП !");
        }

        Subject subject = new Subject();
        subject.setEducation(serviceModel.getEducation());
        subject.setUser(user);
        user.setSubject(subject);
        usersRepository.save(user);
    }

    @Override
    public void createUserComment(String username, CommentCreateServiceModel serviceModel) throws Exception {
        User user = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        Comment comment = new Comment();
        if (serviceModel.getName().isEmpty()) {
            throw new GroupNotFoundException("Моля въведете име!");
        }
        if (serviceModel.getEmail().isEmpty()) {
            throw new GroupNotFoundException("Моля въведете имейл!");
        }
        if (serviceModel.getText().isEmpty()) {
            throw new GroupNotFoundException("Моля въведете текст!");
        }

        comment.setName(serviceModel.getName());
        comment.setEmail(serviceModel.getEmail());
        comment.setText(serviceModel.getText());
        comment.setUser_id(user.getId());
        commentRepository.save(comment);
        usersRepository.save(user);
    }
}
