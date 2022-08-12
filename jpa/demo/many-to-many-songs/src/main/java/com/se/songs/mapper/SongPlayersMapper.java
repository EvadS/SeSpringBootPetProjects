package com.se.songs.mapper;


import com.se.songs.dto.SongPlayersDTO;
import com.se.songs.entity.SongPlayers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper/*(uses = {PeopleMapper.class})*/
public interface SongPlayersMapper {

    SongPlayersMapper SONG_PLAYERS_MAPPER = Mappers.getMapper(SongPlayersMapper.class);

    SongPlayersDTO fromSongPlayers(SongPlayers songPlayers);

    @InheritInverseConfiguration
    SongPlayers toSongPlayers(SongPlayersDTO songPlayersDTO);
}