package com.ashu.demo.controller;


import com.ashu.demo.model.AppUser;
import com.ashu.demo.model.Tweet;
import com.ashu.demo.repository.AppRoleRepository;
import com.ashu.demo.repository.AppUserRepository;
import com.ashu.demo.repository.CommentRepository;
import com.ashu.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AppRoleRepository appRoleRepository;






    @GetMapping("/login")
    public String showLogin(Model model){




        return "login";

    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("register", new AppUser());

        return "register";

    }


    @PostMapping("/register")
    public String registerForm(Model model,@Valid AppUser register ,BindingResult result){


        if (result.hasErrors()) {
            return "register";
        }
        appUserRepository.save(register);


        return "redirect:/login";

    }





    @GetMapping("/")
    public  String showIndex(Model model, Authentication auth){

        //show tweet form , and with it a way for uploading images
        model.addAttribute("tweetForm", new Tweet());

        //list all users on the system , findallusers and a link under each user
        model.addAttribute("allusers", appUserRepository.findAll());

        model.addAttribute("userinfo", new AppUser());

        Long id = appUserRepository.findByUsername(auth.getName()).getId();

        model.addAttribute("tweets", tweetRepository.findByAppUserId(id));
        // so that the currently logged user will be able to follow  from the list
        //when that link is clicked it will show all tweets from that user
        //display the posts in chronoligically reverse order , for that write a repository query


        // to filter items by postdate arranged in reverse order
        //display a link to like posts .. that means the like couter increases ad
        //result is displayed ..
        //there should be a link under each post to add comment
        //comments also need query that display them in reverse chronological order
         //the commenst contain .. whp made the commnet,the message itself and date and time
        //it is posted.

        return "index";
    }

    @PostMapping("/")
    public String processTweet(Model model, @Valid Tweet tweet, BindingResult result,Authentication auth){

        if (result.hasErrors()) {
            return "tweetform";
        }
        AppUser appUser = appUserRepository.findByUsername(auth.getName());

        tweet.setAppUser(appUser);
        tweetRepository.save(tweet);



        appUser.addTweet(tweet);

        appUserRepository.save(appUser);



        return "redirect:/";


    }

    @GetMapping("/update")
    public String updateProfile(Model model){

        model.addAttribute("userForm", new AppUser());

        return "profile";

    }

    @PostMapping("/update")
    String updateUserData(Authentication auth, @Valid AppUser userForm, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {

            return "mypage";

        }
        AppUser appUser = appUserRepository.findByUsername(auth.getName());
        appUser.setDisplayname(userForm.getDisplayname());
        appUserRepository.save(appUser);

        return "redirect:/";
    }

    @PostMapping("/follow/{username}")
    public String follow(Model model, Authentication auth, @PathVariable("username") String username){


        AppUser appUser = appUserRepository.findByUsername(auth.getName());


        appUser.addRole(appRoleRepository.findAppRoleByRolename("USER"));
        appUser.addFollows(appUserRepository.findByUsername(username));

        appUserRepository.save(appUser);

        return "redirect:/";
    }

    @GetMapping("/comment")
    public String showTweet(){


        return "commentform";
    }

    @PostMapping("/comment")
    public String postTweet() {

        return "commentform";

    }


}
