package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.App;
import com.lovushkina.store.domain.Creater;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.CreaterRepository;
import com.lovushkina.store.service.CreaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CreaterServiceImpl implements CreaterService {
    @Autowired
    final CreaterRepository createrRepository;

    @Override
    public List<Creater> findAll() {
        return createrRepository.findAll();
    }

    @Override
    public Creater findById(Integer id) {
        return createrRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Creater", id));
    }

    @Override
    public Creater create(Creater entity) {
        createrRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, Creater entity) {
        Creater creater = createrRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Creater", id));
        creater.setFullName(entity.getFullName());
        creater.setWorkBranch(entity.getWorkBranch());
        creater.setEmail(entity.getEmail());
        creater.setCreatedAppAmount(entity.getCreatedAppAmount());
        createrRepository.save(creater);
    }

    @Override
    public void delete(Integer id) {
        createrRepository.deleteById(id);
    }

    @Override
    public List<App> getAppsByCreaterId(Integer id) {
        Creater creater = createrRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Creater", id));
        return creater.getApps().stream().toList();
    }

    @Override
    public Creater insertCreaterWithProcedures(String full_name, String work_branch, String email,
            Integer created_app_amount) {
        return createrRepository.insertCreaterWithProcedures(full_name, work_branch,
                email, created_app_amount);
    }

    @Override
    public void addManyToManyRelationShip(String app_name, String creater_full_name) {
        createrRepository.addManyToManyRelationShip(app_name, creater_full_name);
    }

    @Override
    public Integer findMaxAppCount() {
        return createrRepository.findMaxAppCount();
    }

}
