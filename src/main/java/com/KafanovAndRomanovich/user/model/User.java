package com.KafanovAndRomanovich.user.model;

import com.KafanovAndRomanovich.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity
@Indexed
@Table(name = "user_accounts")
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Field
    @Column(name = "first_name", length = 100,nullable = false)
    private String firstName;

    @Field
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaService signInProvider;

    private String photo;
    private Date dateofBirth;
    private String city;
    private String education;
    private String skype;
    private String interests;

    private Boolean isBaned=false;

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,
            fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private List<Achievement> achievements=new ArrayList<>(0);

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,
            fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private List<Post> posts;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private List<Comment> comments;


    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Rating> ratings;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private List<Likes> likes;


    public Boolean getBaned() {
        return isBaned;
    }

    public void setBaned(Boolean baned) {
        isBaned = baned;
    }
    public void addComment(Comment comment){comments.add(comment);}
    public void addAchievement(Achievement achievement){
        achievements.add(achievement);
    }
    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public void addRating(Rating rating){ratings.add(rating);}
    public void removeRating(Rating rating) {
        for (int i = 0; i < ratings.size(); i++) {
            if (ratings.get(i).getId().equals(rating.getId())) {
                ratings.remove(i);
                break;
            }
        }
    }
    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addPost(Post post){this.posts.add(post);}
    public void updatePost(Post post) throws ParseException {
        if(post.getId()==null) {
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy");
            String result = format.format(today);
            Date date=format.parse(result);
            post.setDate(date);
            posts.add(post);
            return;
        }
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId().equals(post.getId())) {
                posts.get(i).setCategory(post.getCategory());
                posts.get(i).setTitle(post.getTitle());
                posts.get(i).setImage(post.getImage());
                posts.get(i).setTags(post.getTags());
                posts.get(i).setText(post.getText());
                break;
            }
        }
    }
    public void deletePost(Post post){
        for (int i = 0; i <posts.size(); i++) {
            if (posts.get(i).getId().equals(post.getId())){
                posts.remove(i);
                break;
            }
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSignInProvider(SocialMediaService signInProvider) {
        this.signInProvider = signInProvider;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getPhoto() {
        return photo;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public String getCity() {
        return city;
    }

    public String getEducation() {
        return education;
    }

    public String getSkype() {
        return skype;
    }

    public String getInterests() {
        return interests;
    }

    public User() {
        this.posts=new ArrayList<>();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public SocialMediaService getSignInProvider() {
        return signInProvider;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                //.append("creationTime", this.getCreationTime())
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                //.append("modificationTime", this.getModificationTime())
                .append("signInProvider", this.getSignInProvider())
                .append("version", this.getVersion())
                .toString();
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
            user.role = Role.ROLE_USER;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }
        public Builder photo(String photo){
            user.photo = photo;
            return this;
        }

        public Builder signInProvider(SocialMediaService signInProvider) {
            user.signInProvider = signInProvider;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
