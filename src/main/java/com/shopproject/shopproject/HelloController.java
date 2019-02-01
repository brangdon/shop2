package com.shopproject.shopproject;

/**
 * Created by admin on 18.01.2019.
 */

import com.shopproject.shopproject.domain.User;
import com.shopproject.shopproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000","http://10.254.5.76:3000", "http://10.254.5.65:3000", "http://10.254.5.64:3000", "http://10.254.5.77:3000"})
@RestController
public class HelloController {

    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @GetMapping(path = "/hi", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String hello() {
        String hi = "hello";
        String json = "{\"hi\":\"" + hi + "\"}";
        return json;
    }


    @GetMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String registerUser() {

        User n = new User();
        n.setUsername("ff");
        n.setPassword("ff@ff");
        userRepository.save(n);

        String json = "[{\"message\":\"success\"}}]";
        return json;
    }

//    @GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    String loginUser(@RequestBody Map<String, Object> data) {
//        String name = (String) data.get("name");
//        String email = (String) data.get("email");
//
//        User u = userRepository.findByUsername("aa");
//        if (u != null) {
//
//            System.out.println("=== " + u.getUsername());
//            System.out.println("=== " + u.getPassword());
//            System.out.println("=== " + u.getEmail());
//        } else {
//            System.out.println("=== " + "nope");
//        }
//
//        String json = "[{\"hi\":\"snail\"}, {\"name\":\"laplap\"}]";
//        return json;
//    }

    //
}
