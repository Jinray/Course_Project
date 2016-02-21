package com.KafanovAndRomanovich.user.model;


import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 17.02.2016.
 */
public class PostUser implements Comparable<PostUser> {

    private Long userId;
    private String lastName;
    private String firstName;
    private Long id;
    private String title;
    private String text;
    private Date date;
    private Integer rating;
    private String category;
    private String image;
    private String email;
    private Integer template;
    private List<Comment> comments;
    private List<Tag> tags;
    public Long getUserId() {
        return userId;
    }

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Tag> getTags() {
        return tags;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(PostUser o) {
        if(this.rating<o.rating)return 1;
        if(this.rating>o.rating)return -1;
        return 0;
    }
}
