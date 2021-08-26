package com.sample.controller;

import com.sample.domain.CandidateModel;
import com.sample.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;


    @PostMapping("/")
    public CandidateModel saveCandidate(@RequestBody CandidateModel model) {
        CandidateModel saveModel = candidateService.saveCandidate(model);
        return saveModel;
    }

    @PostMapping("/valid")
    public CandidateModel saveValidCandidate(@RequestBody CandidateModel model) {
        CandidateModel saveModel = candidateService.addValidPeople(model);
        return saveModel;
    }
}