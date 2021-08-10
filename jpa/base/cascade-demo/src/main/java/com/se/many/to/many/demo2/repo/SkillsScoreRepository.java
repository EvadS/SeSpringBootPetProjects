package com.se.many.to.many.demo2.repo;

import com.se.many.to.many.demo2.entity.SkillsScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsScoreRepository extends JpaRepository<SkillsScore, Long> {

}
