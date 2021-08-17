package com.janani.department.controller;

import com.janani.department.entity.Department;
import com.janani.department.repository.DepartmentRepository;
import com.janani.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment method of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById method of DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }

    @PutMapping("/{id}")
    public Department updateDept(@RequestBody Department dept,@PathVariable("id") long departmentId) {
        Department existingDept=this.departmentRepository.findById(departmentId).get();
        existingDept.setDepartmentName(dept.getDepartmentName());
        existingDept.setDepartmentHOD(dept.getDepartmentHOD());
        existingDept.setDepartmentCode(dept.getDepartmentCode());

        return this.departmentRepository.save(existingDept);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDept(@PathVariable ("id") long deptId){
        Department existingDept=this.departmentRepository.findById(deptId).get();
        this.departmentRepository.delete(existingDept);
        return ResponseEntity.ok().build();

    }
}
