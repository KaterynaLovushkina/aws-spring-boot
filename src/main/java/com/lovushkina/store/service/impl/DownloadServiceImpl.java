package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.*;
import com.lovushkina.store.domain.Download;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.DownloadRepository;
import com.lovushkina.store.repository.UserRepository;
import com.lovushkina.store.service.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    final DownloadRepository downloadRepository;

    @Autowired
    final UserRepository userRepository;

    @Override
    public List<Download> findAll() {
        return downloadRepository.findAll();
    }

    @Override
    public Download findById(Integer id) {
        return downloadRepository.findById(id).orElseThrow(() -> new DataNotFoundException("AppCategory", id));
    }

    @Override
    public Download create(Download entity) {
        downloadRepository.save(entity);
        return entity;
    }

    @Override
    public Download create(Download download, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("AppCategory", userId));
        download.setUser(user);
        return downloadRepository.save(download);
    }

    @Override
    public void update(Integer id, Download entity) {
        Download download = downloadRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Download", id));
        download.setAmount(entity.getAmount());
        download.setTotalPrice(entity.getTotalPrice());
        downloadRepository.save(download);
    }

    @Override
    public void update(Integer downloadId, Download entity, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("AppCategory", downloadId));
        Download download = downloadRepository.findById(downloadId)
                .orElseThrow(() -> new DataNotFoundException("Download", userId));
        download.setAmount(entity.getAmount());
        download.setTotalPrice(entity.getTotalPrice());
        download.setUser(user);
        downloadRepository.save(download);

    }

    @Override
    public void delete(Integer id) {
        downloadRepository.deleteById(id);
    }

    @Override
    public List<App> getAppsByDownloadId(Integer id) {
        Download download = downloadRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Download", id));
        return download.getApps().stream().toList();
    }

}
