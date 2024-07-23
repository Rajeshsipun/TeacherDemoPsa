package com.NewSchool.Teacher.Controller;

import com.NewSchool.Teacher.Dto.TeacherDto;
import com.NewSchool.Teacher.Entity.TeacherEntity;
import com.NewSchool.Teacher.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/school")
public class TeacherController {

    private TeacherService teacherService ;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //http://localhost:8080/api/v1/school/teacher

    @PostMapping("/teacher")
    public ResponseEntity<?> addTeacher(
            @Valid
            @RequestBody TeacherDto teacherDto ,
            BindingResult result
            ){

        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
        TeacherDto teacherDto1 = teacherService.addTeacher(teacherDto);

        return  new ResponseEntity<>(teacherDto1, HttpStatus.CREATED);
    }


    //http://localhost:8080/api/v1/school?teacherId=2
    @DeleteMapping
    public ResponseEntity<String>deleteTeacher(@RequestParam long teacherId){
        teacherService.deleteTeacher(teacherId);

        return new ResponseEntity<>("Deleted Teacher Id", HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/school/teacherId/1
@PutMapping("/{teacherId}")
    public ResponseEntity<TeacherEntity>updateTeacherId(
            @PathVariable long teacherId,
            @RequestBody TeacherDto teacherDto
            ){
        TeacherEntity teacherEntity = teacherService.updateTeacherId(teacherDto, teacherId);

        return new ResponseEntity<>(teacherEntity,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/school?pageSize=3&pageNo=0
    @GetMapping
    public ResponseEntity<List<TeacherDto>>getTeacherId(
            @RequestParam(name = "pageSize", defaultValue = "5",required = false)int pageSize,
            @RequestParam(name = "pageNo", defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "shortBy", defaultValue = "id",required = false)String shortBy,
            @RequestParam(name = "shortDir", defaultValue = "id",required = false)String shortDir



    ){
        List<TeacherDto> teacher =  teacherService.getTeacherId(pageSize,pageNo,shortBy,shortDir);
        return  new ResponseEntity<>(teacher,HttpStatus.OK);

    }

    //http://localhost:8080/api/v1/school/getBy?teacherId=4
    @GetMapping("/getBy")
    public ResponseEntity<TeacherEntity>getTeacherById(@RequestParam long teacherId) {
        TeacherEntity teacher = teacherService.getTeacherById(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

}
