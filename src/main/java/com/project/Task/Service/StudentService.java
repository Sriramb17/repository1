package com.project.Task.Service;

import com.project.Task.Model.Login404Response;
import com.project.Task.Model.StudentTable;
import com.project.Task.Model.UserTable;
import com.project.Task.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

@Autowired
    private StudentRepo studentRepo;

    public Optional<StudentTable> getByMail(String email) {
        Optional<StudentTable> mail = Optional.ofNullable(studentRepo.findByEmail(email));
        return mail;
    }
    public Object create(StudentTable studentTable){
        return studentRepo.save(studentTable);
    }
        public Object fetchAll(StudentTable studentTable){
        return studentRepo.findAll();
        }

    public Optional<StudentTable> fetchById(Long id) {
        return studentRepo.findById(id);
    }
    public StudentTable update(Long id, StudentTable studentTable) {
        StudentTable user1 = studentRepo.findById(id).get();
        user1.setEmail(studentTable.getEmail());
       user1.setBirthday(studentTable.getBirthday());
        user1.setFirstName(studentTable.getFirstName());
        user1.setLastName(studentTable.getLastName());
        try {
            studentRepo.save(user1);
        }catch (Exception e) {
            e.getMessage();
        }
        return user1;
    }
    public ResponseEntity<Object> delete(Long id){
        try{
            try {
                studentRepo.deleteById(id);
                return ResponseEntity.status(200).body(new Login404Response("User "+id+" deleted"));
            }catch (Exception e){
                return ResponseEntity.status(404).body(new Login404Response("User Not found"));
            }

        }catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
}
