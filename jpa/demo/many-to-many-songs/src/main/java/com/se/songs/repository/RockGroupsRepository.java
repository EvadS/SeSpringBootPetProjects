package com.se.songs.repository;

import com.se.songs.entity.RockGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RockGroupsRepository extends JpaRepository<RockGroups, Long> {
}