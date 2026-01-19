package com.stemlink.skillmentor.services;

import com.stemlink.skillmentor.entities.Mentor;
import com.stemlink.skillmentor.repositories.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MentorService {

    private final MentorRepository mentorRepository;
    private ModelMapper modelMapper;

    public List<Mentor> getAllMentors(){
        return mentorRepository.findAll();

    }

    public Mentor getMentorById(Long id){
        return mentorRepository.findById(id).get();
    }

    public Mentor createMentor(Mentor mentor){
        return mentorRepository.save(mentor);
    }

    public Mentor updateMentorById(Long id, Mentor updatedMentor){
        Mentor mentor = mentorRepository.findById(id).get();
        modelMapper.map(updatedMentor, Mentor.class);
        return mentorRepository.save(mentor);
    }

    public void deleteMentor(Long id){
        mentorRepository.deleteById(id);
    }

}
