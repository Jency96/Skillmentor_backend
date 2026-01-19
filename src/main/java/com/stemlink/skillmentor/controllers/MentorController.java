package com.stemlink.skillmentor.controllers;

import com.stemlink.skillmentor.dto.MentorDTO;
import com.stemlink.skillmentor.entities.Mentor;
import com.stemlink.skillmentor.services.MentorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final ModelMapper modelMapper;
    private final MentorService mentorService;

    //fetch all the subjects using arrayList
    @GetMapping
    public List<Mentor> getAllMentors(){
        System.out.println("GET");
       return mentorService.getAllMentors();
    }

    @GetMapping("{id}")
    public Mentor getMentorById(@PathVariable Long id){
        System.out.println("GET By Id");
        return mentorService.getMentorById(id);
    }

    @PostMapping
    public Mentor createMentor( @RequestBody MentorDTO mentorDTO){
        System.out.println("POST");
        Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
        return mentorService.createMentor(mentor);
    }

    @PutMapping("{id}")
    public Mentor updateMentorById(@Valid @PathVariable Long id, @RequestBody MentorDTO updatedMentorDTO){
        Mentor mentor = modelMapper.map(updatedMentorDTO, Mentor.class);
        return mentorService.updateMentorById(id,mentor);
    }

    @DeleteMapping("{id}")
    public void deleteMentor(@PathVariable Long id){
        System.out.println("DELETED");
         mentorService.deleteMentor(id);
    }
}
