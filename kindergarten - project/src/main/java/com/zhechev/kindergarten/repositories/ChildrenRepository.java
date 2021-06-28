package com.zhechev.kindergarten.repositories;

import com.zhechev.kindergarten.models.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, Integer> {
}
