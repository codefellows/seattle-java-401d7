package com.soyewale.codefellowship.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToMany
            @JoinTable(
                    name="mingling",
                    joinColumns = {@JoinColumn(name="iDeyFollowThem")},
                    inverseJoinColumns = {@JoinColumn(name="theyDeyFollowMe")}
            )
    Set<Post> iAmFollowing;
    public Set<Post> getIAmFollowing(){
        return this.iAmFollowing;
    }

    public void takeOutIAmFollowing(Post disc ){
        this.iAmFollowing.add(disc);

    }

    @ManyToMany(mappedBy = "iAmFollowing")
    Set<Post> followingMe;

    @ManyToOne
    ApplicationUser applicationUser;

    String body;
    public String createdAt;
    boolean kidsAppropriate;

    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }

//    public String getcreatedAt() {
//        return createdAt;
//    }

    public boolean isKidsAppropriate() {
        return kidsAppropriate;
    }
    public Post(ApplicationUser applicationUser, String body, String createdAt, boolean kidsAppropriate) {
        this.applicationUser = applicationUser;
        this.body = body;
        this.createdAt = createdAt;
        this.kidsAppropriate = kidsAppropriate;
    }
    public Post(){

    }
}

