package com.lovushkina.store.repository;

import com.lovushkina.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure("CreateTablesWithCursor")
    void createTablesWithСursor();
}
