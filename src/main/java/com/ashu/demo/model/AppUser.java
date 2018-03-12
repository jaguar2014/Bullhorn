package com.ashu.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String displayname;

    private String picurl;


    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<AppUser> followers;

    @ManyToMany
    @JoinTable(name = "follows",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "follows_id"))
    private List<AppUser> follows;

@OneToMany(mappedBy = "appUser",cascade = CascadeType.PERSIST)
    private Set<Tweet> tweets;


@OneToMany(mappedBy = "appUser",cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addFollower(AppUser follower){

        this.followers.add(follower);

    }

    public void addFollows(AppUser follows){
        this.follows.add(follows);

    }


    public List<AppUser> getFollowers() {
        return followers;
    }

    public void setFollowers(List<AppUser> followers) {
        this.followers = followers;
    }

    public List<AppUser> getFollows() {
        return follows;
    }

    public void setFollows(List<AppUser> follows) {
        this.follows = follows;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Transient //Equivalent to an ignore statement
    private PasswordEncoder encoder;


    @Transient //Equivalent to an ignore statement
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public AppUser(String username, String password, AppRole role) {
        this.username = username;
        this.roles = new HashSet<>();


        addRole(role);
        encoder = passwordEncoder();
        setPassword(password);

    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> roles;

    public AppUser() {
        this.roles = new HashSet<>();
        this.tweets = new HashSet<>();
        encoder = passwordEncoder();
        this.followers = new ArrayList<>();
        this.follows = new ArrayList<>();
        this.comments = new HashSet<>();

    }

    public void addRole(AppRole r) {
        this.roles.add(r);
    }

    public long getId() {
        return id;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encoder.encode(password);
        System.out.println("Password:"+this.password);
    }


}
