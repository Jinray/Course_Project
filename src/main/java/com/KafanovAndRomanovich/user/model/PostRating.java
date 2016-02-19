package com.KafanovAndRomanovich.user.model;

/**
 * Created by Alex on 18.02.2016.
 */
public class PostRating {
    private Long postId;
    private boolean positive;

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public Long getPostId() {

        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
