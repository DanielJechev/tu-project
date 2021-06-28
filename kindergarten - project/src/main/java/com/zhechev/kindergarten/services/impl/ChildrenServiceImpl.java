package com.zhechev.kindergarten.services.impl;

import com.zhechev.kindergarten.dtos.ChildrenCreateServiceModel;
import com.zhechev.kindergarten.repositories.ChildrenRepository;
import com.zhechev.kindergarten.services.ChildrenService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChildrenServiceImpl implements ChildrenService {
    private final ChildrenRepository childrenRepository;
    private final ModelMapper mapper;

    @Override
    public boolean areThereChildren() {
        return childrenRepository.count() > 1;
    }

    @Override
    public List<ChildrenCreateServiceModel> getChild(String childrenName) {
        return childrenRepository.findAll()
                .stream()
                .filter(child -> !child.getName().equals(childrenName))
                .map(hero -> mapper.map(hero, ChildrenCreateServiceModel.class))
                .collect(Collectors.toList());
    }
}

