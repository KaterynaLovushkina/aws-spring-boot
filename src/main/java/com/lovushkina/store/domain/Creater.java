package com.lovushkina.store.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "creater", schema = "lovushkina", catalog = "")
public class Creater {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "work_branch")
    private String workBranch;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "created_app_amount")
    private Integer createdAppAmount;
    @ManyToMany
    @JoinTable(name = "app_creating", catalog = "", schema = "lovushkina", joinColumns = @JoinColumn(name = "creater_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id", nullable = false))
    private Set<App> apps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkBranch() {
        return workBranch;
    }

    public void setWorkBranch(String workBranch) {
        this.workBranch = workBranch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreatedAppAmount() {
        return createdAppAmount;
    }

    public void setCreatedAppAmount(Integer createdAppAmount) {
        this.createdAppAmount = createdAppAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Creater that = (Creater) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName)
                && Objects.equals(workBranch, that.workBranch) && Objects.equals(email, that.email)
                && Objects.equals(createdAppAmount, that.createdAppAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, workBranch, email, createdAppAmount);
    }

    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }
}
