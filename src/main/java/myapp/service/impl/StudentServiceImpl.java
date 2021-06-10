package myapp.service.impl;

import myapp.service.StudentService;
import myapp.domain.Student;
import myapp.repository.StudentRepository;
import myapp.service.dto.StudentInputDTO;
import myapp.service.dto.StudentResultDTO;
import myapp.service.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Student}.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResultDTO save(StudentInputDTO studentInputDTO) {
        log.debug("Request to save Student : {}", studentInputDTO);
        Student student = studentMapper.toEntity(studentInputDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentResultDTO update(Long id, StudentInputDTO studentInputDTO) {
        log.debug("Request to save Student : {}", studentInputDTO);
        Student student = studentMapper.toEntity(studentInputDTO);
        student.setId(id);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentResultDTO> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id)
            .map(studentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }
}
