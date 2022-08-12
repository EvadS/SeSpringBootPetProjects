package com.se.songs.mapper;


import com.se.songs.dto.AlbumDTO;
import com.se.songs.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper/*(uses = {SongPlayersMapper.class})*/
public interface AlbumMapper {

    AlbumMapper ALBUM_MAPPER = Mappers.getMapper(AlbumMapper.class);

    AlbumDTO fromAlbum(Album album);

    /*@InheritInverseConfiguration
    Album toAlbum(AlbumDTO albumDTO);*/
}