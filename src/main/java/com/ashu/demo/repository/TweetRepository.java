package com.ashu.demo.repository;

import com.ashu.demo.model.Tweet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TweetRepository extends CrudRepository<Tweet, Long> {


    List<Tweet> findByAppUserId(Long id);


    List<Tweet> findByAppUserIdInOrderByDatepostedDesc(List<String> ids);

    List<Tweet> findByAppUser_IdIsInOrderByDatepostedDesc(List<Long> ids);


}


