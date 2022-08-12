package com.se.element.collection.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "searcher")
public class JobSearcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    public JobSearcher() {
    }

    //declare an element-collection mapping
    @ElementCollection(fetch = FetchType.EAGER)

    @CollectionTable(name = "seacher_skill", joinColumns = @JoinColumn(name = "searcher_id"))
//    @AttributeOverrides({
//            @AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
//            @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
//    })
    private Set<SearchersSkills> searchersSkills = new HashSet<>();

    public JobSearcher( @NotNull @Size(max = 100) String name, Set<SearchersSkills> searchersSkills) {
          this.name = name;
        this.searchersSkills = searchersSkills;
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

    public Set<SearchersSkills> getSearchersSkills() {
        return searchersSkills;
    }

    public void setSearchersSkills(Set<SearchersSkills> searchersSkills) {
        this.searchersSkills = searchersSkills;
    }

    @Override
    public String toString() {
        return "JobSearcher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seachersSkills=" + searchersSkills +
                '}';
    }
}
