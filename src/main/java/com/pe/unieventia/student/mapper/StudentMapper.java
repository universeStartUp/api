package com.pe.unieventia.student.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.resource.StudentResource;
import com.pe.unieventia.student.resource.StudentResponseResource;

@Component
public class StudentMapper {
    
    private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Student resourceToEntity(StudentResource studentResource) {
        return modelMapper.map(studentResource, Student.class);
    }

    public StudentResource entityToResource(Student student) {
        return modelMapper.map(student, StudentResource.class);
    }

    public StudentResponseResource entityToResponseResource(Student student) {
        return modelMapper.map(student, StudentResponseResource.class);
    }

    public List<Student> resourceListToEntityList(List<StudentResource> studentResources) {
        return studentResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<StudentResource> entityListToResourceList(List<Student> students) {
        return students
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<StudentResponseResource> entityListToResponseResourceList(List<Student> students) {
        return students
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}
