##  bidirectional association
Пример двусторонней ассоциации

@Embeddable
public class PostTagId оставной ключ
postId
tagId
Замечания

There are two very important
-You need the @Embeddable type to be Serializable
 The @Embeddable type must override the default equals and hashCode methods based on the two Primary Key identifier values.
--------------
PostTag таблица связка
    @EmbeddedId
    private PostTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    private Tag tag;

 --------------------------------
 со стороны Tag @OneToMany
 private List<PostTag> posts = new ArrayList<>();

со стороны Post
@OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PostTag> tags = new ArrayList<>();

    имеет служебные методы **addTag* и **removeTag**

-----------------
В случае односторонней связи
в Post
public void addTag(Tag tag) {
    PostTag postTag = new PostTag(this, tag);
    tags.add(postTag);
}

public void removeTag(Tag tag) {
    for (Iterator<PostTag> iterator = tags.iterator();
         iterator.hasNext(); ) {
        PostTag postTag = iterator.next();

        if (postTag.getPost().equals(this) &&
                postTag.getTag().equals(tag)) {
            iterator.remove();
            postTag.setPost(null);
            postTag.setTag(null);
        }
    }
}

собсно entity без явного указщания связи
```java
@Entity(name = "Tag")
@Table(name = "tag")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    //Getters omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
```



