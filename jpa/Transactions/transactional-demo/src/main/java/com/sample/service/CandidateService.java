package com.sample.service;

import com.sample.domain.CandidateModel;
import com.sample.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    public CandidateModel saveCandidate(CandidateModel model) {
        CandidateModel candidateModel = candidateRepository.save(model);
        try {
            int a = 12 / 0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return candidateModel;
    }
}
