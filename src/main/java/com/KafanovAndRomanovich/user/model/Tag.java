package com.KafanovAndRomanovich.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Indexed
@Table(name = "tags")
public class Tag implements Comparable<Tag> {
    @Id
    @GeneratedValue
    private Long id;
    @Field
    private String text;
    private Integer weight=1;
    @JsonIgnore
    @ManyToMany( mappedBy = "tags",fetch = FetchType.EAGER)
    private List<Post> posts=new ArrayList<>(0);

    public Long getTagId() {
        return id;
    }

    public void setTagId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Tag o) {
        if(this.weight<o.weight)return 1;
        if(this.weight>o.weight)return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", weight=" + weight +
                ", posts=" + posts +
                '}';
    }
}
