package com.zhechev.kindergarten.services.impl;

import com.zhechev.kindergarten.models.User;
import com.zhechev.kindergarten.repositories.UsersRepository;
import com.zhechev.kindergarten.exceptions.KindergartenNotFoundException;
import com.zhechev.kindergarten.dtos.UserRegisterDto;
import com.zhechev.kindergarten.services.AuthService;
import com.zhechev.kindergarten.dtos.LoginUserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private UsersRepository usersRepository;

    @Autowired
    public AuthServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User register(UserRegisterDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        Example<User> example = Example.of(user);
        Optional<User> optionalUser = usersRepository.findOne(example);
        if (!optionalUser.isEmpty()) {
            throw new KindergartenNotFoundException("Моля,опитайте отново.Потребител с този имейл вече съществува.");
        }

        User user1 = new User();
        user1.setUsername(userDto.getUsername());
        user.setUsername(user1.getUsername());
        Example<User> example1 = Example.of(user1);
        Optional<User> optionalUser1 = usersRepository.findOne(example1);
        if (optionalUser1.isPresent()) {
            throw new KindergartenNotFoundException("Моля,опитайте отново! Потребител с това име вече съществува.");
        }

        User egnUser = new User();
        egnUser.setEgn(userDto.getEgn());
        user.setEgn(egnUser.getEgn());
        Example<User> egnExample = Example.of(egnUser);
        Optional<User> egnOptional = usersRepository.findOne(egnExample);
        if (egnOptional.isPresent()) {
            throw new KindergartenNotFoundException("Моля,опитайте отново! Потребител с това ЕГН вече съществува.");
        }

        if (userDto.getUsername().isEmpty()) {
            throw new KindergartenNotFoundException("Моля,въведете валидно потребителско име. Полето не може да бъде празно !");
        }
        if (userDto.getEmail().isEmpty()) {
            throw new KindergartenNotFoundException("Моля,въведете валиден имейл. Полето не може да бъде празно !");
        }

        if (userDto.getEgn().isEmpty()) {
            throw new KindergartenNotFoundException("Моля,въведете валидно ЕГН. Полето не може да бъде празно !");
        }

        if (userDto.getAddress().isEmpty()) {
            throw new KindergartenNotFoundException("Моля,въведете валиден адрес. Полето не може да бъде празно !");
        }
        if (userDto.getPhone().isEmpty()) {
            throw new KindergartenNotFoundException("Моля,въведете валиден телефонен номер. Полето не може да бъде празно !");
        }
        if (userDto.getPassword().isEmpty()) {
            throw new KindergartenNotFoundException("Моля,въведете валидна парола. Полето не може да бъде празно !");
        }

        if (!Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
            throw new KindergartenNotFoundException("Моля,опитайте отново,паролите трябва да съвпадат !");
        }

        user.setPassword(hashPassword(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        return usersRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(UserRegisterDto userRegisterDto) {
        String passwordHash = hashPassword(userRegisterDto.getPassword());
        return usersRepository
                .findByUsernameAndPassword(userRegisterDto.getUsername(), passwordHash)
                .map(user -> {
                    String name = user.getChildren() == null
                            ? null
                            : user.getChildren().getName();

                    String subject = user.getSubject() == null
                            ? null
                            : user.getSubject().getEducation().toString();
                    String group = user.getGrupa() == null
                            ? null
                            : user.getGrupa().getVid().toString();

                    return new LoginUserServiceModel(userRegisterDto.getUsername(), name, user.getEmail(), user.getPhone(), user.getAddress(), subject, group);
                })
                .orElseThrow(() -> new KindergartenNotFoundException("Invalid user"));
    }

    @Override
    public String hashPassword(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
