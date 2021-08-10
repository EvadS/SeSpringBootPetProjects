package com.se.many.to.many.demo2.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "skills_score")
public class SkillsScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "seacher_skills",
            joinColumns = @JoinColumn(name = "skills_score_id"),
            inverseJoinColumns = @JoinColumn(name = "searcher_id"))
    private List<Searcher> searchers = new ArrayList<>();

    public SkillsScore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Searcher> getSearchers() {
        return searchers;
    }

    public void setSearchers(List<Searcher> searchers) {
        this.searchers = searchers;
    }

    public void addSearcher(Searcher b) {
        this.searchers.add(b);
        b.getSkillsScore().add(this);
    }

    public void removeBook(Searcher b) {
        this.searchers.remove(b);
        b.getSkillsScore().remove(this);
    }
}
