package com.se.sample;

import com.se.sample.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class TestDataGenerator {

    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;

    @Autowired
    private SpeakerRepository speakerRepository;

    public SpeakerDataBuilder speaker(String speakerName) {
        return new SpeakerDataBuilder(speakerRepository).speaker(speakerName);
    }


}
