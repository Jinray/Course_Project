package com.KafanovAndRomanovich.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 09.02.2016.
 */
@Entity
@Indexed
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @Field
    private String text;
    private Date date;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "comment")
    @Fetch(FetchMode.SELECT)
    private List<Likes> likes;
    public void removeLike(Likes like){likes.remove(like);}
    public List<Likes> getLikes() {
        return likes;
    }
    public void addLike(Likes like){likes.add(like);}
    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;



    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
