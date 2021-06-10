package myapp.service.impl;

import myapp.service.TeacherService;
import myapp.domain.Teacher;
import myapp.repository.TeacherRepository;
import myapp.service.dto.TeacherInputDTO;
import myapp.service.dto.TeacherResultDTO;
import myapp.service.mapper.TeacherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Teacher}.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherResultDTO save(TeacherInputDTO teacherInputDTO) {
        log.debug("Request to save Teacher : {}", teacherInputDTO);
        Teacher teacher = teacherMapper.toEntity(teacherInputDTO);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherResultDTO update(Long id, TeacherInputDTO teacherInputDTO) {
        log.debug("Request to save Teacher : {}", teacherInputDTO);
        Teacher teacher = teacherMapper.toEntity(teacherInputDTO);
        teacher.setId(id);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherResultDTO> findOne(Long id) {
        log.debug("Request to get Teacher : {}", id);
        return teacherRepository.findById(id)
            .map(teacherMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teacher : {}", id);
        teacherRepository.deleteById(id);
    }
}
