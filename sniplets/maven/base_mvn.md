 
pre-defined variables in the POM.

```xml
<version>${project.version}</version>
```

Available Variables

Any field of the model that is a single value element can be referenced as a variable. For example,
${project.groupId}
${project.version}
${project.build.sourceDirectory}

Command Line Arguments as Maven Properties

```xml
<properties>
    <maven.compiler.source>1.7</maven.compiler.source>
    <commons.version>2.5</commons.version>
    <version>${COMMON_VERSION_CMD}</version>
</properties>
```

```bash
    mvn package -DCOMMON_VERSION_CMD=2.5
```
