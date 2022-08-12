package com.se.spec;


import com.se.spec.domain.Note;
import com.se.spec.dto.NoteRequest;
import com.se.spec.repository.NoteRepository;
import com.se.spec.specification.NoteSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.main.banner-mode=off")
@Transactional
public class SpecificationTest {

    @Autowired
    NoteRepository noteRepository;


    @Autowired
    NoteSpecification noteSpecification;

    // not correct test, cause we used data frm command line runner on @SpringBootAppplication
    @Test
    public void testMembersActive() {


        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setEndPeriod(946887345000l);
        noteRequest.setStartedPeriod(946714545000l);

        List<Note> memberList = noteRepository.findAll(noteSpecification.getFilter(noteRequest));
       Assert.assertEquals(3 , memberList.size());
    }
}
