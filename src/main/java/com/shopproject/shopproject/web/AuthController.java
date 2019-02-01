package com.shopproject.shopproject.web;

import com.shopproject.shopproject.domain.User;
import com.shopproject.shopproject.repository.UserRepository;
import com.shopproject.shopproject.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = {"http://localhost:3000","http://10.254.5.76:3000", "http://10.254.5.65:3000", "http://10.254.5.64:3000", "http://10.254.5.77:3000"})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, Arrays.asList("ROLE_USER"));

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            model.put("roles",  "ROLE_USER");

            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @GetMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String registerUser() {

        User u = new User("aa", "bb", Arrays.asList("ROLE_USER"));
        userRepository.save(u);

        String json = "[{\"message\":\"success\"}}]";
        return json;
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getUser() {

        Optional<User> u = userRepository.findByUsername("admin");
        if (u != null) {

            System.out.println("=== " + u.get().getUsername());
            System.out.println("=== " + u.get().getPassword());
            System.out.println("=== " + u.get().getAuthorities().toString());
        } else {
            System.out.println("=== " + "nope");
        }

        String json = "[{\"hi\":\"snail\"}, {\"name\":\"laplap\"}]";
        return json;
    }

}
