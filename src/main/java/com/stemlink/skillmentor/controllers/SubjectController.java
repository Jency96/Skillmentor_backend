package com.stemlink.skillmentor.controllers;

import com.stemlink.skillmentor.dto.SubjectDTO;
import com.stemlink.skillmentor.entities.Subject;
import com.stemlink.skillmentor.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final ModelMapper modelMapper;
    private final SubjectService subjectService;

    //path parameter --> id
    //query parameter --> @RequestParam (?name=John,roll=admin)

    //create mock database
    //put initial data
//    private final List<Subject> subjects = new ArrayList<>((
//            List.of(new Subject("Maths", "MT-101"),
//                    new Subject("Science","MT-102" ))
//            ));

    //fetch all the subjects using arrayList
    @GetMapping
    public List<Subject> getAllSubjects(@RequestParam(name = "name",defaultValue = "all") String name){
       // System.out.println("GET");
       // System.out.println("filter By name " +name);
        return subjectService.getAllSubjects();
    }

    @GetMapping("{id}")
    public String getSubjectById(@PathVariable int id){
        System.out.println("GET By Id");
        return "get subject by id " +id;
    }
//
//    @PostMapping
//    public Subject createSubject( @RequestBody @Valid SubjectDTO subjectDTO){

        //validation to check subject name
//        if (subject.getSubjectName().length() > 20){
//            Subject errorSubject = new Subject();
//            errorSubject.setSubjectName(" ");
//            errorSubject.setDescription(" ");
//            return errorSubject;
//        }

        //mapping SubjectDTO to Subject
//        Subject subject = new Subject();
//        subject.setSubjectName(subjectDTO.getSubjectName());
//        subject.setDescription(subjectDTO.getDescription());

        //now use model mapper for mapping
//        Subject subject = modelMapper.map(subjectDTO,Subject.class);
//
//
//        System.out.println("POST");
////        subjects.add(subject);
//        return subject;
//    }

    @PostMapping
    public Subject createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {

        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        //return subjectService.addNewSubject(subjectDTO.getMentorId(), subject);
        return subjectService.addNewSubject(subject);
    }

    @PutMapping("{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody SubjectDTO updatedSubjectDTO) {
        Subject subject = modelMapper.map(updatedSubjectDTO, Subject.class);
        return subjectService.updateSubjectById(id, subject);
    }

    @DeleteMapping("{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

}
