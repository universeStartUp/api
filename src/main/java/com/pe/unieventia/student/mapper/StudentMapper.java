package com.pe.unieventia.student.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pe.unieventia.student.domain.entity.Student;
import com.pe.unieventia.student.dto.input.StudentDTO;
import com.pe.unieventia.student.dto.response.StudentResponseDTO;

@Component
public class StudentMapper {
    
    private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Student dtoToEntity(StudentDTO studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public StudentDTO entityToDto(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentResponseDTO entityToResponseDto(Student student) {
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    public List<Student> dtoListToEntityList(List<StudentDTO> studentDtos) {
        return studentDtos
                .stream()
                .map(this::dtoToEntity)
                .toList();
    }

    public List<StudentDTO> entityListToDtoList(List<Student> students) {
        return students
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    public List<StudentResponseDTO> entityListToResponseDtoList(List<Student> students) {
        return students
                .stream()
                .map(this::entityToResponseDto)
                .toList();
    }
}
