package com.se.many.to.many.demo2;

import com.se.many.to.many.demo2.entity.Searcher;
import com.se.many.to.many.demo2.entity.SkillsScore;
import com.se.many.to.many.demo2.repo.SearcherRepository;
import com.se.many.to.many.demo2.repo.SkillsScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class SkillSearcherStartup implements CommandLineRunner {

    @Autowired
    private SkillsScoreRepository skillsScoreRepo;

    @Autowired
    private SearcherRepository searcherRepo;

    @Override
    public void run(String... args) throws Exception {
        searcherRepo.deleteAllInBatch();
        skillsScoreRepo.deleteAllInBatch();

        Searcher searcher = new Searcher();
        searcher.setName("searcher 1 ");
        searcherRepo.save(searcher);

        Searcher searcher2 = new Searcher();
        searcher2.setName("searcher 2 ");
        searcherRepo.save(searcher2);

        Searcher searcher3 = new Searcher();
        searcher3.setName("searcher 3.");
        searcherRepo.save(searcher3);

        SkillsScore skillsScore = new SkillsScore();
        skillsScore.setName("skill 1");
        //    skillsScore.setSearchers(Arrays.asList(searcher));
        ///  skillsScore.setSearchers(Arrays.asList(searcher2));

        //  skillsScore.getSearchers().add(searcher3);
        skillsScoreRepo.save(skillsScore);

        SkillsScore skillsScore2 = new SkillsScore();
        skillsScore2.setName("skill 2");
        skillsScoreRepo.save(skillsScore2);


        skillsScore2.addSearcher(searcher);
        skillsScoreRepo.save(skillsScore2);

        List<Searcher> searchers = skillsScore2.getSearchers();

        for (Searcher s : searchers) {
            skillsScore2.removeBook(s);
            skillsScoreRepo.save(skillsScore2);
        }

        skillsScore2.addSearcher(searcher);
        skillsScore2.addSearcher(searcher2);
        skillsScoreRepo.save(skillsScore2);


        // edit

//
//        searcher2.setName("searcher 21.");
//        searcher2.setSkillsScore();
//        searcherRepo.save(searcher2);


    }
}
