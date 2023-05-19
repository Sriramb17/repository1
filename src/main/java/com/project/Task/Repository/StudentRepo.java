package com.project.Task.Repository;

import com.project.Task.Model.StudentTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentTable,Long> {

    StudentTable findByEmail(String email);
}
