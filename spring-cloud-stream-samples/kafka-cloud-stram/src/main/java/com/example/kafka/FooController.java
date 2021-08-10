package com.example.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private InteractiveQueryService interactiveQueryService;

//    @RequestMapping("/song/id")
//    public SongBean song(@RequestParam(value="id") Long id) {
//
//        final ReadOnlyKeyValueStore<Long, Song> songStore =
//                interactiveQueryService.getQueryableStore("STORE-NAME",
//                        QueryableStoreTypes.<Long, Song>keyValueStore());
//
//        final Song song = songStore.get(id);
//        if (song == null) {
//            throw new IllegalArgumentException("Song not found.");
//        }
//        return new SongBean(song.getArtist(), song.getAlbum(), song.getName());
//    }
}