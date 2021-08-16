package com.sample.service;

import com.sample.domain.CandidateModel;
import com.sample.repository.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
@Transactional
public class CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateService.class);

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateValidateService validateService;


    public CandidateModel saveCandidate(CandidateModel model) {
        CandidateModel candidateModel = candidateRepository.save(model);
        try {
            int a = 12 / 0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return candidateModel;
    }

    @Transactional
    public CandidateModel addValidPeople(CandidateModel candidateModel) {
        candidateRepository.saveAndFlush(new CandidateModel("Jack", "23", "man"));
        candidateRepository.saveAndFlush(new CandidateModel("Julia", "33", "woman"));

        printLog(candidateModel);
        String resultName = candidateModel.getName();
        try {
            validateService.validateName(candidateModel.getName());
        } catch (IllegalArgumentException e) {
            logger.error("name is not allowed. Using default one");
            resultName = "DefaultName";
        }
        return candidateRepository.saveAndFlush(new CandidateModel(resultName, "18", "Unknown"));
    }



    private void printLog(CandidateModel model) {

        logger.info("************************************ --> ");
        logger.info("=== {}", model);

        logger.info(" < -- ************************************");
    }

}
