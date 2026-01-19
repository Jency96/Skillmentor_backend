package com.stemlink.skillmentor.services;

import com.stemlink.skillmentor.entities.Subject;
import com.stemlink.skillmentor.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    private final List<Subject> subjects = new ArrayList<>();

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll(); // SELECT * from subject
    }

    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id).get(); // ... WHERE id=={}
    }

    public Subject addNewSubject(Subject subject){
       // Mentor mentor = mentorRepository.findById(mentorId).get();
       // subject.setMentor(mentor);
        return subjectRepository.save(subject); // INSERT
    }

    public Subject updateSubjectById(Long id, Subject updatedSubject){

        Subject subject = subjectRepository.findById(id).get();
        subject.setSubjectName(updatedSubject.getSubjectName());
        subject.setDescription(updatedSubject.getDescription());
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id){
        subjectRepository.deleteById(id);
    }
}
