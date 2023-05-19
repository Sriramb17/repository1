package com.project.Task.Controller;

import com.project.Task.Model.*;
import com.project.Task.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private PasswordEncode pass;

    @PostMapping
    public Object createAll(@RequestBody UserTable userTable){
              return loginService.create(userTable);
    }

    @PostMapping("/user/changepassword")
    public Object changePassword(@RequestBody UserTable userTable){
        try{
            Optional<UserTable> user1 = loginService.getByMail(userTable.getEmail());
            Optional<UserTable> user2=loginService.changePass(userTable.getPassword());
            if(user1.isPresent()){
                Object s=loginService.create(user2.get());
                return ResponseEntity.status(200).body(s);
            }else{
                return ResponseEntity.status(404).body(new Login404Response("Email or password does not match"));
            }
        }catch (Exception e){
               throw new IllegalStateException(e.getMessage());
        }

    }

    @PostMapping("/users")
    public Object create(@RequestBody UserTable userTable){
        try {
            Optional<UserTable> user1 = loginService.getByMail(userTable.getEmail());
            if (user1.isPresent()) {
                    return ResponseEntity.status(403).body(new Login404Response("Access Denied"));
                } else {
                    Object user2 = loginService.create(userTable);
                    return ResponseEntity.status(201).body(user2);
                }
        }catch (Exception e){
                 throw new IllegalStateException(e.getMessage());
        }
    }
    @PostMapping("/user/login")
    public Object login(@RequestBody LoginResponse loginResponse){
        try {
            String email=loginResponse.getEmail();
           Optional<UserTable> user=loginService.getByMail(email);
            if(user.isPresent()){
                UserTable data2=user.get();
                if(pass.encoder().matches(loginResponse.getPassword(), data2.getPassword()))
                {
//                    data2.setPassword(null);
                    return ResponseEntity.status(200).body(new Response("Bearer",pass.generateJwt(data2)));
                }else{
                    return  ResponseEntity.status(404).body(new Login404Response("Email or password does not match"));
                }
            }else{
                return  ResponseEntity.status(404).body(new Login404Response("Email or password does not match"));
            }
        }catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
    @GetMapping("/users")
    public Object fetch(UserTable userTable) {
        try {
            Optional<Object> obj = Optional.of(loginService.fetchAll(userTable));
            if (obj.isPresent()) {
                return obj;
            } else if (!obj.isPresent()){
                return ResponseEntity.status(200).body(new Login404Response("//No User found"));
            }else {
                return ResponseEntity.status(403).body(new Login404Response("Access denied"));
            }
        } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getGroupById(@PathVariable("id") Long id) {
        Optional<UserTable> user1 = loginService.fetchById(id);
        try {
            if (user1.isPresent()) {
                if (user1.get().isActive()) {
                    return ResponseEntity.status(200).body(user1.get());
                } else {
                    return ResponseEntity.status(403).body(new Login404Response("Access Denied"));
                }
            }
             else{
                    return ResponseEntity.status(404).body(new Login404Response("User (" + id + ") not found"));
                }
        }catch(Exception e){
                throw new IllegalStateException(e.getMessage());
            }
        }

        @PutMapping("/users/{id}")
             public ResponseEntity<Object> updateById(@PathVariable("id") Long id,@RequestBody UserTable userTable) {
            try {
                Optional<UserTable> user2 = Optional.of(loginService.update(id, userTable));
                if (user2.isPresent()) {
                    if (user2.get().isActive()) {
                        return ResponseEntity.status(200).body(user2);
                    } else {
                        return null;
                    }
                } else {
                    return ResponseEntity.status(403).body(new Login404Response("Access denied"));
                }
            }catch (Exception e){
                String email = userTable.getEmail();
                return ResponseEntity.status(403).body(new Login404Response("User ["+email+"] not found"));
            }
        }
        @DeleteMapping("/users/{id}")
        public Object deleteBy(@PathVariable("id") Long id,UserTable userTable){
          Object obj= loginService.delete(id);
          return obj;
     }


}
