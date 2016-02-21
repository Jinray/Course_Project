package com.KafanovAndRomanovich.user.service;

import com.KafanovAndRomanovich.user.model.Post;
import com.KafanovAndRomanovich.user.model.PostUser;
import com.KafanovAndRomanovich.user.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 12.02.2016.
 */
@Service
@Transactional
public class RepositoryPostService implements PostService {
    @Autowired
    RatingService ratingService;
    PostRepository postRepository;
    @Autowired
    public RepositoryPostService(PostRepository postRepository) {
        this.postRepository=postRepository;
    }

    @Override
    public Post findOne(Long id) {
        return postRepository.findOne(id);
    }




    @Override
    public Post save(Post post) {

        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByCategory(String category) {
        return postRepository.findByCategory(category);
    }



    public List<PostUser> getAllPosts(List<Post> posts){
        PostUser postUser;
        List<PostUser> result=new ArrayList<>();
        for (Post post :posts) {
            postUser=new PostUser();
            postUser.setUserId(post.getUser().getId());
            postUser.setId(post.getId());
            postUser.setText(post.getText());
            postUser.setTitle(post.getTitle());
            postUser.setImage(post.getImage());
            postUser.setDate(post.getDate());
            postUser.setCategory(post.getCategory());
            postUser.setFirstName(post.getUser().getFirstName());
            postUser.setLastName(post.getUser().getLastName());
            postUser.setEmail(post.getUser().getEmail());
            postUser.setComments(post.getComments());
            postUser.setTemplate(post.getTemplate());
            postUser.setRating(ratingService.getScore(post));
            postUser.setTags(post.getTags());
            result.add(postUser);
        }
        return result;
    }


}
