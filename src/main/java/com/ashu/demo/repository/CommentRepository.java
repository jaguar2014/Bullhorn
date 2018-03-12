package com.ashu.demo.repository;

import com.ashu.demo.model.AppUser;
import com.ashu.demo.model.Comment;
import com.ashu.demo.model.Tweet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    List<Comment> findByAppUser(AppUser appUser);
    List<Comment> findByTweet(Tweet tweet);
}
