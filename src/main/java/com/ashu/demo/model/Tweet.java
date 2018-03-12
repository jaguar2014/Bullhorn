package com.ashu.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String message;

    @CreationTimestamp
    private LocalDateTime dateposted;


    @ManyToOne
    private AppUser appUser;

    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL)
    private Set<Comment> comments;



    public Tweet(){
        this.comments = new HashSet<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateposted() {
        return dateposted;
    }

    public void setDateposted(LocalDateTime dateposted) {
        this.dateposted = dateposted;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


}
