package com.programmingpointer.rest.webservices.restful_web_services.controller;

import com.programmingpointer.rest.webservices.restful_web_services.dao.PostRepo;
import com.programmingpointer.rest.webservices.restful_web_services.dao.UserRepo;
import com.programmingpointer.rest.webservices.restful_web_services.exception.UserNotFoundException;
import com.programmingpointer.rest.webservices.restful_web_services.model.Post;
import com.programmingpointer.rest.webservices.restful_web_services.model.User;
import com.programmingpointer.rest.webservices.restful_web_services.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);



        if(user.isEmpty()){
            throw new UserNotFoundException("user not found with ID: "+id);
        }

        EntityModel entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteByID(@PathVariable int id){
        userRepo.deleteById(id);
    }


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("User not found with ID: "+id);

        return  user.get().getPosts();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepo.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
      Optional<User> user = userRepo.findById(id);
      if(user.isEmpty())
          throw new UserNotFoundException("User not found ID: "+id);

      post.setUser(user.get());
      Post savedPost = postRepo.save(post);

      //return user.get().getPosts();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
