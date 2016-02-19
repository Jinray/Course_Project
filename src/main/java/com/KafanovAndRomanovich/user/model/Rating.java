package com.KafanovAndRomanovich.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by Alex on 17.02.2016.
 */
@Entity
@Table(name="rating")
public class Rating {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean positive;

    public Boolean getPositive() {
        return positive;
    }

    public void setPositive(Boolean positive) {
        this.positive = positive;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    @JsonIgnore
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", positive=" + positive +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
