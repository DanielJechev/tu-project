package com.zhechev.kindergarten.repositories;

import com.zhechev.kindergarten.models.Grupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupaRepository extends JpaRepository<Grupa,Integer> {
}
