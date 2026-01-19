package com.stemlink.skillmentor.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mentors")

public class MentorController {
    private List<String> mentors = new ArrayList<>((
            List.of("John", "Peter","Jenny")
    ));

    //fetch all the subjects using arrayList
    @GetMapping
    public String getAllMentors(@RequestParam(name = "name",defaultValue = "all") String name){
        System.out.println("GET");
        System.out.println("filter By name " +name);
       return mentors.toString();
    }

    @GetMapping("{id}")
    public String getMentorById(@PathVariable int id){
        System.out.println("GET By Id");
        return "get subject by id " +id;
    }

    @PostMapping
    public String createMentor(){
        System.out.println("POST");
        return "created subjects";
    }

    @PutMapping("{id}")
    public String updateMentorById(@PathVariable int id){
        System.out.println("UPDATED");
        return "updated subject by id {}";
    }

    @DeleteMapping("{id}")
    public String deleteMentor(@PathVariable int id){
        System.out.println("DELETED");
        mentors.remove(id);
        return "deleted subject by id {}";
    }
}
