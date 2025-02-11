package com.programmingpointer.rest.webservices.restful_web_services.dao;

import com.programmingpointer.rest.webservices.restful_web_services.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

}
