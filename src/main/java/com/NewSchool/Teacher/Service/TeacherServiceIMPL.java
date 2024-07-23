package com.NewSchool.Teacher.Service;


import com.NewSchool.Teacher.Dto.TeacherDto;
import com.NewSchool.Teacher.Entity.TeacherEntity;
import com.NewSchool.Teacher.Exception.ResourceNotFoundException;
import com.NewSchool.Teacher.Repository.TeacherEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TeacherServiceIMPL implements TeacherService{

    private TeacherEntityRepository teacherEntityRepository ;

    public TeacherServiceIMPL(TeacherEntityRepository teacherEntityRepository) {
        this.teacherEntityRepository = teacherEntityRepository;
    }


    @Override
    public TeacherDto addTeacher(TeacherDto dto) {

        TeacherEntity teacherEntity = dtoTOEntity(dto);
        TeacherEntity saveTeacher = teacherEntityRepository.save(teacherEntity);
        TeacherDto teacherDto = entityToDto(saveTeacher);

        return teacherDto;
    }

    public TeacherEntity dtoTOEntity(TeacherDto teacherDto ){
        TeacherEntity teacherEntity = new TeacherEntity() ;
        teacherEntity.setName(teacherDto.getName());
        teacherEntity.setMobile(teacherDto.getMobile());
        teacherEntity.setEmailId(teacherDto.getEmailId());
        teacherEntity.setDepartment(teacherDto.getDepartment());

        return teacherEntity;

    }

    public TeacherDto entityToDto(TeacherEntity teacherEntity ){
        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId(teacherEntity.getId());
        teacherDto.setName(teacherEntity.getName());
        teacherDto.setMobile(teacherEntity.getMobile());
        teacherDto.setEmailId(teacherDto.getEmailId());
        teacherDto.setDepartment(teacherEntity.getDepartment());

        return teacherDto;
    }

    @Override
    public void deleteTeacher(long teacherId) {

        teacherEntityRepository.deleteById(teacherId);
    }

    @Override
    public TeacherEntity updateTeacherId(TeacherDto teacherDto, long teacherId) {
        Optional<TeacherEntity> byId = teacherEntityRepository.findById(teacherId);
        TeacherEntity teacherEntity = byId.get();

        teacherEntity.setName(teacherDto.getName());
        teacherEntity.setMobile(teacherDto.getMobile());
        teacherEntity.setEmailId(teacherDto.getEmailId());
        teacherEntity.setDepartment(teacherDto.getDepartment());

        return teacherEntity ;
    }

    @Override
    public List<TeacherDto> getTeacherId(int pageSize, int pageNo, String shortBy, String shortDir) {
        Pageable pageable = null;

        if (
                shortDir.equalsIgnoreCase("asc")
        ){
             pageable = PageRequest.of(pageNo, pageSize, Sort.by(shortBy).ascending());
        }else if (shortDir.equalsIgnoreCase("desc")){
             pageable = PageRequest.of(pageNo, pageSize, Sort.by(shortBy).descending());
        }


        Page<TeacherEntity> all = teacherEntityRepository.findAll(pageable);

        List<TeacherEntity> teacherEn = all.getContent();
        List<TeacherDto> teacherDt = teacherEn.stream().map(p -> entityToDto(p)).collect(Collectors.toList());
        return teacherDt;
    }

    @Override
    public TeacherEntity getTeacherById(long teacherId) {
        TeacherEntity teacherEntity = teacherEntityRepository.findById(teacherId).orElseThrow(
                ()-> new ResourceNotFoundException("User  not found with Id"+ teacherId)
        );
        return teacherEntity;
    }
}
