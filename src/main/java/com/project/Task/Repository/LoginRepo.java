package com.project.Task.Repository;

import com.project.Task.Model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
@Repository
public interface LoginRepo extends JpaRepository<UserTable,Long> {

    @Query(value = "select * from tbl_user where email=?1 and password=?2",nativeQuery = true)
    UserTable loginUser1(@PathVariable("email") String email,@PathVariable("password") String password);

    @Query(value = "select * from tbl_user where password=?1",nativeQuery = true)
    UserTable changePassword(@PathVariable("password") String password);

    UserTable findByEmail(String email);


   // Optional<UserTable> existsById(Optional<UserTable> fetchById);
}
