package com.smit_proovitoo.backend.repository;

import com.smit_proovitoo.backend.model.Procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}
