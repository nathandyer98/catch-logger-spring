package com.catchlogger.backend.repository;

import com.catchlogger.backend.model.Catch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatchRepository extends JpaRepository<Catch, Long> {
}