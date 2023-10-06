package com.lovushkina.store.repository;

import com.lovushkina.store.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends JpaRepository<App, Integer> {

    @Query("SELECT app FROM App app WHERE app.isFree=true")
    List<App> getAllFreeApps();
}
