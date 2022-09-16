package com.example.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String>{
    List<EmployeeDocument> findAllByBirthYearBetweenAndJobStyleAndDepartment(int fromYear,int toYear,JobStyle style,Department department);
    @Query("{'yil': {$gte: ?1, $lt: ?2}}")
    List<EmployeeDocument> yillaraGoreGetir(int fromYear,int toYear);
    
}
