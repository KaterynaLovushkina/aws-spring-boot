package com.lovushkina.store.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "lovushkina", catalog = "")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "audience_type")
    private String audienceType;
    @OneToMany(mappedBy = "category")
    private List<App> apps;
    @ManyToOne
    @JoinColumn(name = "app_category_id", referencedColumnName = "id", nullable = false)
    private AppCategory appCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAudienceType() {
        return audienceType;
    }

    public void setAudienceType(String audienceType) {
        this.audienceType = audienceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Category that = (Category) o;
        return Objects.equals(id, that.id) && Objects.equals(audienceType, that.audienceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, audienceType);
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public AppCategory getAppCategory() {
        return appCategory;
    }

    public void setAppCategory(AppCategory appCategory) {
        this.appCategory = appCategory;
    }
}
