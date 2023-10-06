package com.lovushkina.store.repository;

import com.lovushkina.store.domain.AppCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCategoryRepository extends JpaRepository<AppCategory, Integer> {

    @Procedure("Insert10Rows")
    void insert10Rows(String name, String description);

}