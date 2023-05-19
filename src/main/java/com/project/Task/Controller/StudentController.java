package com.project.Task.Controller;

import com.project.Task.Model.Login404Response;
import com.project.Task.Model.StudentTable;
import com.project.Task.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Object create(@RequestBody StudentTable studentTable){
        try {
            Optional<StudentTable> user1 = studentService.getByMail(studentTable.getEmail());
            if (user1.isPresent()) {
                return ResponseEntity.status(403).body(new Login404Response("Access Denied"));
            } else {
                Object user2 = studentService.create(studentTable);
                return ResponseEntity.status(201).body(user2);
            }
        }catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
    @GetMapping("/list")
    public Object fetch(StudentTable studentTable) {
        try {
            Optional<Object> obj = Optional.of(studentService.fetchAll(studentTable));
            if (obj.isPresent()) {
                return obj;
            } else if (obj.toString().isEmpty()){
                return ResponseEntity.status(200).body(new Login404Response("//No User found"));
            }else {
                return ResponseEntity.status(403).body(new Login404Response("Access denied"));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Long id) {
        Optional<StudentTable> user1 = studentService.fetchById(id);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") Long id,@RequestBody StudentTable studentTable) {
        try {
            Optional<StudentTable> user2 = Optional.of(studentService.update(id, studentTable));
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
            String email = studentTable.getEmail();
            return ResponseEntity.status(403).body(new Login404Response("User ["+email+"] not found"));
        }
    }
    @DeleteMapping("/delete/{id}")
    public Object deleteBy(@PathVariable("id") Long id,StudentTable studentTable){
        Object obj= studentService.delete(id);
        return obj;
    }
}
