package com.se.embeded.demo.web;



import com.se.embeded.demo.entity.Song;
import com.se.embeded.demo.model.SongDto;
import com.se.embeded.demo.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
public class SongsController {
    @Autowired
    SongsService service;

    @GetMapping("/artist/{artist}/album/{album}/name/{name}")
    public Song getSong(@PathVariable String artist, @PathVariable String album, @PathVariable String name) {
        SongDto dto = new SongDto();
        dto.setName(name);
        dto.setArtist(artist);
        dto.setAlbum(album);

        return service.find(dto);
    }

    @PostMapping
    public Song addSong(@RequestBody SongDto dto) {
        return service.addSong(dto);
    }
}
