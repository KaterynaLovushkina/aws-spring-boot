package com.lovushkina.store.domain;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "email_preferences", schema = "lovushkina", catalog = "")
public class EmailPreferences extends RepresentationModel<EmailPreferences> {

    @Id
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "keep_with_up_to_date_news")
    private Boolean keepWithUpToDateNews;
    @Basic
    @Column(name = "receive_reply_notification")
    private Boolean receiveReplyNotification;
    @Basic
    @Column(name = "user_id")
    private Integer userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getKeepWithUpToDateNews() {
        return keepWithUpToDateNews;
    }

    public void setKeepWithUpToDateNews(Boolean keepWithUpToDateNews) {
        this.keepWithUpToDateNews = keepWithUpToDateNews;
    }

    public Boolean getReceiveReplyNotification() {
        return receiveReplyNotification;
    }

    public void setReceiveReplyNotification(Boolean receiveReplyNotification) {
        this.receiveReplyNotification = receiveReplyNotification;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmailPreferences that = (EmailPreferences) o;
        return Objects.equals(email, that.email) && Objects.equals(keepWithUpToDateNews, that.keepWithUpToDateNews)
                && Objects.equals(receiveReplyNotification, that.receiveReplyNotification)
                && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, keepWithUpToDateNews, receiveReplyNotification, userId);
    }
}
