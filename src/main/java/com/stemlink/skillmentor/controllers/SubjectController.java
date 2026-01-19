package com.stemlink.skillmentor.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {

    //path parameter --> id
    //query parameter --> @RequestParam (?name=John,roll=admin)

    //create mock database
    //put initial data
    private List<String> subjects = new ArrayList<>((
            List.of("Math", "Science","History")
            ));

    //fetch all the subjects using arrayList
    @GetMapping
    public String getAllSubjects(@RequestParam(name = "name",defaultValue = "all") String name){
        System.out.println("GET");
        System.out.println("filter By name " +name);
        return subjects.toString();
    }

    @GetMapping("{id}")
    public String getSubjectById(@PathVariable int id){
        System.out.println("GET By Id");
        return "get subject by id " +id;
    }

    @PostMapping
    public String createSubject(){
        System.out.println("POST");
        return "created subjects";
    }

    @PutMapping("{id}")
    public String updateSubjectById(@PathVariable int id){
        System.out.println("UPDATED");
        return "updated subject by id {}";
    }

    @DeleteMapping("{id}")
    public String deleteSubject(@PathVariable int id){
        System.out.println("DELETED");
        subjects.remove(id);
        return "deleted subject by id {}";
    }

}
