package com.lovushkina.store.repository;

import com.lovushkina.store.domain.EmailPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailPreferencesRepository extends JpaRepository<EmailPreferences, String> {
}
