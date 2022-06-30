package com.se.sample.persist;

import com.se.sample.Audit;
import com.se.sample.TaskConfiguration;
import com.se.sample.enums.TaskLog;
import com.se.sample.enums.TaskStatus;
import com.se.sample.enums.TaskType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cm_remote_server_task")
@Entity(name = "cm_remote_server_task")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RemoteServerTaskEntity {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "ID", length = 36, unique = true, nullable = false)
    protected UUID id = UUID.randomUUID();

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36)
    private UUID traceId;

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, nullable = false)
    private UUID serverId;

    @Type(type = "jsonb")
    @Column(name = "configuration", columnDefinition = "json")
    private TaskConfiguration configuration;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Type(type = "jsonb")
    @Column(name = "log", columnDefinition = "json")
    private TaskLog log;

    @Column(nullable = false)
    private boolean notified;
//
    @Embedded
    @Builder.Default
    private Audit audit = new Audit();
}
