package com.NewSchool.Teacher.Service;

import com.NewSchool.Teacher.Dto.TeacherDto;
import com.NewSchool.Teacher.Entity.TeacherEntity;

import java.util.List;

public interface TeacherService {
    TeacherDto addTeacher(TeacherDto teacherDto);

    void deleteTeacher(long teacherId);

    TeacherEntity updateTeacherId(TeacherDto teacherDto, long teacherId);

    List<TeacherDto> getTeacherId(int pageSize, int pageNo, String shortBy, String shortDir);


    TeacherEntity getTeacherById(long teacherId);
}
