package com.cliente.escola.grade_curricular.repository;

import com.cliente.escola.grade_curricular.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {
}
