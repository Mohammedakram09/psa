package com.sample.controller;

import com.sample.entity.Student;
import com.sample.payload.StudentDto;
import com.sample.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(
            @Valid @RequestBody StudentDto dto,
            BindingResult result
    ){
        if(result.hasErrors()){
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StudentDto studentDto = studentService.addStudent(dto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<StudentDto> deleteStudent(@RequestParam Long id){
        StudentDto studentDto = studentService.deleteById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestParam Long id,@Valid @RequestBody StudentDto dto){
        StudentDto studentDto = studentService.updateStudent(id, dto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudentAll(){
        List<StudentDto> studentDtos = studentService.getStudentAll();
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }
    @GetMapping("/stu/{sid}")
    public ResponseEntity<StudentDto> getStudentAll(
            @PathVariable Long sid
    ){
        StudentDto studentDto = studentService.getStudentById(sid);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);



    }

    }


