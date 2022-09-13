package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
     List<EmployeeEntity> findAllByBirthYearBetweenAndJobStyleAndDepartment(int fromYear,int toYear,JobStyle style,Department department);
     @Query("""
     	select e from EmployeeEntity e 
     	where e.birthYear between :fromYear and :toYear
     	and e.jobStyle = :style
     	and e.department = :dept
     """) // JPaQL (JPQL)
     List<EmployeeEntity> getir(int fromYear,int toYear,JobStyle style,Department dept);
     @Query(nativeQuery = true, value="""
     	select e from employees e 
     	where e.yil between :fromYear and :toYear
     	and e.jobstyle = :style
     	and e.department = :dept
     """) // JPaQL (JPQL)
     List<EmployeeEntity> nativGetir(int fromYear,int toYear,JobStyle style,Department dept);
}
