package com.programmingpointer.rest.webservices.restful_web_services.service;

import com.programmingpointer.rest.webservices.restful_web_services.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
 private static List<User> users = new ArrayList<>();

 private static int userCount = 0;

 static {
     users.add(new User(++userCount, "Vipul", LocalDate.now().minusYears(27)));
     users.add(new User(++userCount, "Aj", LocalDate.now().minusYears(30)));
     users.add(new User(++userCount, "RK", LocalDate.now().minusYears(35)));
 }

 public List<User> findAll(){
     return users;
 }

 public User save(User user){
     user.setId(++userCount);
     users.add(user);
     return user;
 }

 public User findById(Integer id){

//     for(User user : users){
//         if(user.getId() == id)
//             return user;
//     }
//     return new User();

     return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
 }


    public void deleteByID(int id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
