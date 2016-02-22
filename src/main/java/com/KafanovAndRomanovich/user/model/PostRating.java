package com.KafanovAndRomanovich.user.model;


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
