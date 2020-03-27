package com.soyewale.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    // users following me
    @ManyToMany
    @JoinTable(
            name="mingling",
            joinColumns = {@JoinColumn(name="iDeyFollowThem")},
            inverseJoinColumns = {@JoinColumn(name="theyDeyFollowMe")}
    )
    public Set<ApplicationUser> usersFollowingMe;
    // users I am following
    @ManyToMany(mappedBy = "usersFollowingMe")
    public Set<ApplicationUser> usersIAmFollowing;


    @OneToMany(mappedBy = "applicationUser")  //must match property name on Post
    List<Post> posts ;

    public List<Post> getPosts(){
        return this.posts;
    }

    String username;
    String password;
    String firstName;
    String lastName;
    String dateOfBirth;
    String bio;

    public ApplicationUser() {

    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
