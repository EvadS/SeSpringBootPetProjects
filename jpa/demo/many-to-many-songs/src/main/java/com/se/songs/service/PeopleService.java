package com.se.songs.service;



import com.se.songs.dto.PeopleDTO;
import com.se.songs.entity.People;
import com.se.songs.entity.RockGroups;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PeopleService {
    List<PeopleDTO> getAllPeople();

    PeopleDTO getPeopleById(long id);

    People addPeople(People people);

    void delPeople(long id);

    ResponseEntity<Object> updPeople(People people, long id);

    List<RockGroups> getByHuman(String human);

    List<String> getSongByHuman(String human);
}