package com.lovushkina.store.service;

import com.lovushkina.store.domain.App;

import java.util.List;

public interface AppService extends GeneralService<App, Integer> {
    App create(App app, Integer category);

    List<App> getAppsByCategoryId(Integer categoryId);

    List<App> getAllFreeApps();

    void update(Integer appId, App app, Integer categoryId);
}
