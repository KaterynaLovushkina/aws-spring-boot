package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.App;
import com.lovushkina.store.domain.EmailPreferences;
import com.lovushkina.store.domain.User;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.AppRepository;
import com.lovushkina.store.repository.EmailPreferencesRepository;
import com.lovushkina.store.repository.UserRepository;
import com.lovushkina.store.service.EmailPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class EmailPreferencesServiceImpl implements EmailPreferencesService {
    @Autowired
    final EmailPreferencesRepository emailPreferencesRepository;

    ;

    @Override
    public List<EmailPreferences> findAll() {
        return emailPreferencesRepository.findAll();
    }

    @Override
    public EmailPreferences findById(String email) {
        return emailPreferencesRepository.findById(email)
                .orElseThrow(() -> new DataNotFoundException("AppCategory", email));
    }

    @Override
    public EmailPreferences create(EmailPreferences entity) {
        emailPreferencesRepository.save(entity);
        return entity;
    }

    @Override
    public void update(String email, EmailPreferences entity) {
        EmailPreferences emailPreferences = emailPreferencesRepository.findById(email)
                .orElseThrow(() -> new DataNotFoundException("EmailPreferences", email));
        emailPreferences.setEmail(entity.getEmail());
        emailPreferences.setKeepWithUpToDateNews(entity.getKeepWithUpToDateNews());
        emailPreferences.setReceiveReplyNotification(entity.getReceiveReplyNotification());
        emailPreferences.setUserId(entity.getUserId());
        emailPreferencesRepository.save(emailPreferences);
    }

    @Override
    public void delete(String email) {
        emailPreferencesRepository.deleteById(email);
    }

}
