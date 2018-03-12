package com.ashu.demo.repository;

import com.ashu.demo.model.Tweet;
import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet,Long> {
}
