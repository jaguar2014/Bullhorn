package com.ashu.demo.setup;

import com.ashu.demo.model.AppRole;
import com.ashu.demo.model.AppUser;
import com.ashu.demo.model.Comment;
import com.ashu.demo.model.Tweet;
import com.ashu.demo.repository.AppRoleRepository;
import com.ashu.demo.repository.AppUserRepository;
import com.ashu.demo.repository.CommentRepository;
import com.ashu.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    AppRoleRepository roleRepo;

    @Autowired
    AppUserRepository userRepo;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    CommentRepository commentRepository;




    @Override
    public void run(String... args) throws Exception {


        AppRole role = new AppRole("USER");
        roleRepo.save(role);

        AppUser appUser = new AppUser();
        appUser.addRole(role);
        appUser.setUsername("Ashu");
        appUser.setPassword("password");
        userRepo.save(appUser);

        Tweet tweet = new Tweet();
        tweet.setMessage("my first tweet");

         tweetRepository.save(tweet);
        Comment comment = new Comment();
        comment.setComment("my first comment");
        tweet.addComment(comment);

        appUser.addTweet(tweet);
        userRepo.save(appUser);




    }
}