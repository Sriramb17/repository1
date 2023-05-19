package com.project.Task.Service;

import com.project.Task.Model.Login404Response;
import com.project.Task.Model.LoginResponse;
import com.project.Task.Model.PasswordEncode;
import com.project.Task.Model.UserTable;
import com.project.Task.Repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private PasswordEncode pass;

    public Object create(UserTable userTable) {
        String o = pass.encoder().encode(userTable.getPassword());
        userTable.setPassword(o);
        Optional<UserTable> obj = Optional.of(loginRepo.save(userTable));
        if (obj.isPresent()) {
            return obj;
        } else {
            return ResponseEntity.status(401).body(new Login404Response("Invalid token or expired"));
        }
    }
    public UserTable createA(UserTable userTable){
        return loginRepo.save(userTable);
    }
    public Optional<UserTable> changePass(String password){
        Optional<UserTable> password1 = Optional.ofNullable(loginRepo.changePassword(password));
        return password1;
    }
    public Optional<UserTable> getByMail(String email) {
        Optional<UserTable> mail = Optional.ofNullable(loginRepo.findByEmail(email));
        return mail;
    }

    public Object loginUser(LoginResponse loginResponse) {
        return loginRepo.loginUser1(loginResponse.getEmail(), loginResponse.getPassword());
    }

    public Object fetchAll(UserTable userTable) {
        return loginRepo.findAll();
    }

    public Optional<UserTable> fetchById(Long id) {
        return loginRepo.findById(id);
    }

    public UserTable update(Long id, UserTable userTable) {
        UserTable user1 = loginRepo.findById(id).get();
        user1.setEmail(userTable.getEmail());
        user1.setPassword(userTable.getPassword());
        user1.setFirstName(userTable.getFirstName());
        user1.setLastName(userTable.getLastName());
        try {
          loginRepo.save(user1);
        }catch (Exception e) {
                e.getMessage();
            }
        return user1;
    }

    public ResponseEntity<Object> delete(Long id){
      try{
        try {
            loginRepo.deleteById(id);
            return ResponseEntity.status(200).body(new Login404Response("User "+id+" deleted"));
        }catch (Exception e){
            return ResponseEntity.status(404).body(new Login404Response("User Not found"));
            }

        }catch (Exception e){
         throw new IllegalStateException(e.getMessage());
        }
    }
}
