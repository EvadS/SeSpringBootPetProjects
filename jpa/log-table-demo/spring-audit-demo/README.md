# Spring Boot Audit Demo

Hibernate Envers project aimed to track data changes at the entity level with easy configurations in properties level and entity class level using annotations. The spring-data-envers project builds on top of Hibernate Envers and comes up as an extension of the Spring Data JPA project.

## Build with Maven
Minimum dependencies need to implement the project.
```
<dependency>
	<groupId>org.springframework.data</groupId>
	<artifactId>spring-data-envers</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## Get the Revisions History
findRevisions(postID) Returns all Revisions of an entity with the given post id. 

    public List<Post> getPostEditHistory(Integer postID) {
    
	    List<Post> historyList = new ArrayList<Post>();
    
	    postRepository.findRevisions(postID).get().forEach(x -> {
		    x.getEntity().setEditVersion(x.getMetadata());
			historyList.add(x.getEntity());
		});
	    
	    return historyList;
    }


Spring.jpa.properties.org.hibernate.envers.audit_table_prefix — Change history table prefix
Spring.jpa.properties.org.hibernate.envers.audit_table_suffix — Change history table suffix (Default value — _AUD)
Spring.jpa.properties.org.hibernate.envers.revision_field_name — Change the name of the filed which is maintained version number (Default value — REV)
Spring.jpa.properties.org.hibernate.envers.revision_type_field_name — Change the name of the filed which is maintained change type (Update/Delete/Create) (Default value — REVTYPE)




![picture alt](img/data_base_schema.png)