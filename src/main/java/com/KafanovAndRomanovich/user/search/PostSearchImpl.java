package com.KafanovAndRomanovich.user.search;

import com.KafanovAndRomanovich.user.model.Post;
import javafx.geometry.Pos;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class PostSearchImpl implements Search {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> search(String text) {
        org.hibernate.search.jpa.FullTextQuery jpaQuery = getFullTextQuery(text);
        @SuppressWarnings("unchecked")
        List<Post> results = jpaQuery.getResultList();
        return results;
    }

    private FullTextQuery getFullTextQuery(String text) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager();
        QueryBuilder queryBuilder = getQueryBuilder(fullTextEntityManager);
        org.apache.lucene.search.Query query = getQuery(text, queryBuilder);
        return fullTextEntityManager.createFullTextQuery(query, Post.class);
    }

    private FullTextEntityManager getFullTextEntityManager() {
        return org.hibernate.search.jpa.Search.
                getFullTextEntityManager(entityManager);
    }

    private QueryBuilder getQueryBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Post.class).get();
    }

    private Query getQuery(String text, QueryBuilder queryBuilder) {
        return queryBuilder
                .keyword()
                .onFields("title", "text", "category", "user.firstName", "user.lastName",
                        "comments.text", "tags.text")
                .matching(text.toLowerCase())
                .createQuery();
    }


}


