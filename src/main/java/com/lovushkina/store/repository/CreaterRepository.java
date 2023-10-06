package com.lovushkina.store.repository;

import com.lovushkina.store.domain.Creater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CreaterRepository extends JpaRepository<Creater, Integer> {

    @Procedure("CreaterInsert")
    Creater insertCreaterWithProcedures(String full_name, String work_branch,
            String email, Integer created_app_amount);

    @Procedure("ManyToManyRelationShip")
    void addManyToManyRelationShip(String app_name, String creater_full_name);

    @Query(value = "CALL FindMaxAppCount();", nativeQuery = true)
    Integer findMaxAppCount();

}
