package com.se.sample.persist;

import com.vladmihalcea.hibernate.type.json.JsonBlobType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@TypeDefs({ @TypeDef(name = "jsonb-lob", typeClass = JsonBlobType.class) })
@Data
@Table(name = "a_hyperform_common_config")
@Entity
public class CommonConfig implements Serializable {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, nullable = false)
    private UUID id;

    @Type(type = "jsonb-lob")
    @Column(name = "config_data")
    private String configData;

}