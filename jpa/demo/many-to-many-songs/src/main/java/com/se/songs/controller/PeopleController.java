package com.se.songs.controller;


import com.se.songs.dto.PeopleDTO;
import com.se.songs.dto.SongPlayersDTO;
import com.se.songs.entity.People;
import com.se.songs.entity.RockGroups;
import com.se.songs.entity.SongPlayers;
import com.se.songs.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleRepository repository;

    @GetMapping("/all")
    public List<PeopleDTO> getAllPeople(){
        PeopleDTO peopleDTO = new PeopleDTO();
        return peopleDTO.getPeopleDTOList(repository.findAll());
    }

    //в каких рок группах участвовал данный исполнитель
    @GetMapping("/getbyhuman/{human}")
    public List<RockGroups> getByHuman(@PathVariable String human){
        PeopleDTO peopleDTO = new PeopleDTO();
        List<PeopleDTO> peopleDTOList = peopleDTO.getPeopleDTOList(repository.searchByHuman(human));
        List<RockGroups> rockGroupsList = new ArrayList<>();
        for (PeopleDTO pplDTO : peopleDTOList){
            rockGroupsList.add(pplDTO.getRockGroups());
        }
        return rockGroupsList;
    }
    //найти все композиции заданного исполнителя
    @GetMapping("/getsongbyhuman/{human}")
    public List<SongPlayers> getSongByHuman(@PathVariable String human){
        PeopleDTO peopleDTO = new PeopleDTO();
        List<PeopleDTO> peopleDTOList = peopleDTO.getPeopleDTOList(repository.findPeopleByHuman(human));
        List<List<SongPlayersDTO>> songPlayersDTOList = new ArrayList<>();
        for (PeopleDTO pplDTO : peopleDTOList){
            songPlayersDTOList.add(pplDTO.getSongPlayersList());
        }
        List<SongPlayers> songPlayersList = new ArrayList<>();
        for (List<SongPlayersDTO> sngPlDTOList : songPlayersDTOList){
            for (SongPlayersDTO sngPlDTO : sngPlDTOList){
                songPlayersList.add(new SongPlayers(sngPlDTO.getId(), sngPlDTO.getSong()));
            }
        }
        return songPlayersList;
    }
    /*@GetMapping("/getbyhuman/{human}")
    public List<PeopleDTO> getByHuman(@PathVariable String human){
        PeopleDTO peopleDTO = new PeopleDTO();
        return peopleDTO.getPeopleDTOList(repository.searchByHuman(human));
    }*/


    @PostMapping("/add")
    public People addPeople(@RequestBody People people){
        return repository.save(people);
    }

    @DeleteMapping("/del/{id}")
    public void delPeople(@PathVariable long id){
        repository.deleteById(id);
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<People> updPeople(@RequestBody People people, @PathVariable long id){
        Optional<People> peopleOptional = repository.findById(id);
        if(!peopleOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        people.setId(id);
        repository.save(people);
        return ResponseEntity.noContent().build();
    }
}