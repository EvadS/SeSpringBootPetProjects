package com.se.songs.dto;


import com.se.songs.entity.Album;
import com.se.songs.entity.SongPlayers;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
public class AlbumDTO {
    private long id;
    private String album;
    private List<SongPlayersDTO> songPlayersList;

    @Transactional
    public List<AlbumDTO> getAllAlbumDTOList(List<Album> albumList){
        List<AlbumDTO> albumDTOList = new ArrayList<>();

        for (Album album : albumList){
            songPlayersList = new ArrayList<>();

            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setId(album.getId());
            albumDTO.setAlbum(album.getAlbum());
            for (SongPlayers songPlayers : album.getSongPlayersList()){
                SongPlayersDTO songPlayersDTO = new SongPlayersDTO();

                songPlayersDTO.setId(songPlayers.getId());
                songPlayersDTO.setSong(songPlayers.getSong());
                songPlayersDTO.setComposer(songPlayers.getComposer());
                songPlayersDTO.setAlbum(songPlayers.getAlbum());
                songPlayersDTO.setPoet(songPlayers.getPoet());
                songPlayersList.add(songPlayersDTO);
            }
            albumDTO.setSongPlayersList(songPlayersList);
            albumDTOList.add(albumDTO);
        }
        return albumDTOList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<SongPlayersDTO> getSongPlayersList() {
        return songPlayersList;
    }

    public void setSongPlayersList(List<SongPlayersDTO> songPlayersList) {
        this.songPlayersList = songPlayersList;
    }
}