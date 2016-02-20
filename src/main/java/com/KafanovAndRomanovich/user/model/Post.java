package com.KafanovAndRomanovich.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 09.02.2016.
 */
@Entity
@Indexed
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @Field(index=Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String title;

    @Column(name = "text", columnDefinition="TEXT")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String text;

    @Column(name = "date", columnDefinition="DATE")
    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
    @DateBridge(resolution=Resolution.DAY)
    private Date date;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String category;
    private String image;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "post",cascade=CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }
    public void addRating(Rating rating){ratings.add(rating);}
    public void removeRating(Rating rating){ratings.remove(rating);}
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @IndexedEmbedded
    @OneToMany(mappedBy = "post",cascade=CascadeType.ALL,
            fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Comment> comments;

    @IndexedEmbedded
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "posts_tags", joinColumns = {
            @JoinColumn(name = "POSTS_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TAGS_ID",
                    nullable = false, updatable = false) })
    private List<Tag> tags;




    public Post() {
        this.comments=new ArrayList<>();
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
