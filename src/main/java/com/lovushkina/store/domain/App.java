package com.lovushkina.store.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app", schema = "lovushkina", catalog = "")
public class App {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "weight_in_mb")
    private Integer weightInMb;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "is_free")
    private Boolean isFree;
    @Basic
    @Column(name = "price_in_dollars")
    private BigDecimal priceInDollars;
    @Basic
    @Column(name = "has_advert")
    private Boolean hasAdvert;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "app")
    private List<Feedback> feedbacks;
    @ManyToMany(mappedBy = "apps")
    private Set<Creater> creaters;

    public Set<Download> getDownloads() {
        return downloads;
    }

    public void setDownloads(Set<Download> downloads) {
        this.downloads = downloads;
    }

    @ManyToMany(mappedBy = "apps")
    private Set<Download> downloads;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeightInMb() {
        return weightInMb;
    }

    public void setWeightInMb(Integer weightInMb) {
        this.weightInMb = weightInMb;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getPriceInDollars() {
        return priceInDollars;
    }

    public void setPriceInDollars(BigDecimal priceInDollars) {
        this.priceInDollars = priceInDollars;
    }

    public Boolean getHasAdvert() {
        return hasAdvert;
    }

    public void setHasAdvert(Boolean hasAdvert) {
        this.hasAdvert = hasAdvert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        App appEntity = (App) o;
        return Objects.equals(id, appEntity.id) && Objects.equals(name, appEntity.name)
                && Objects.equals(description, appEntity.description)
                && Objects.equals(weightInMb, appEntity.weightInMb) && Objects.equals(createDate, appEntity.createDate)
                && Objects.equals(isFree, appEntity.isFree) && Objects.equals(priceInDollars, appEntity.priceInDollars)
                && Objects.equals(hasAdvert, appEntity.hasAdvert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, weightInMb, createDate, isFree, priceInDollars, hasAdvert);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<Creater> getCreaters() {
        return creaters;
    }

    public void setCreaters(Set<Creater> creaters) {
        this.creaters = creaters;
    }
}
