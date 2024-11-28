package com.sample.service;

import com.sample.entity.Student;
import com.sample.payload.StudentDto;
import com.sample.repoistory.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDto addStudent(StudentDto dto) {
        Student student = mapToEntity(dto);
        return mapToDto(studentRepository.save(student));


    }

    public StudentDto deleteById(Long id) {
        Optional<Student> opst = studentRepository.findById(id);
        if (opst.isPresent()) {
            studentRepository.deleteById(id);
            return mapToDto(opst.get());
        }
        return null;

    }


    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = mapToEntity(dto);
        student.setId(id);
        return mapToDto(studentRepository.save(student));

    }


    public List<StudentDto> getStudentAll() {
        List<Student> stu = studentRepository.findAll();
        List<StudentDto> dto = stu.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;

    }

    StudentDto mapToDto(Student student) {
        StudentDto dto = modelMapper.map(student, StudentDto.class);
        return dto;

    }
    Student mapToEntity(StudentDto dto) {
        Student s = modelMapper.map(dto, Student.class);
        return s;
    }

    public StudentDto getStudentById(Long sid) {
        Student student = studentRepository.findById(sid).orElseThrow(
                () -> new RuntimeException("Student not found with id: " + sid));
        return mapToDto(student);



    }
}
