 

###  maven with profile
```bash
    mvn package -P dev-local
```

###  maven alternate pom

```bash
    mvn package -P dev-local  -f pom-jenkins-jdk1_8.xml
```
###  maven alternate pom and alternate settings.xml

```bash
    mvn -s settings.xml package -P dev-local  -f pom-jenkins-jdk1_8.xml
```

#### settings and alternate pon in  forkjoin

```bash
 mvn -s /home/softkit/.m2/settings.xml package -P dev-local  -f pom-jenkins-jdk1_8.xml
```

### Maven Dependency Tree to File
```bash
  mvn dependency:tree > /imn/app/log-dependecies
```

```bash
  mvn dependency:tree  -P dev-local > /imn/app/log-dependecies
```

### to include omitted nodes
```bash
  mvn dependency:tree  -P dev-local -Dverbose > /imn/app/log-dependecies
```


### online dependency tree generator.
```
http://101coder.com/genTreeUI
```


### filtered to locate specific dependencies
```bash
    mvn dependency:tree -Dincludes=log4j:log4j
```

### with profile
```bash
    mvn dependency:tree -Dincludes=log4j:log4j -P dev-local
```

### displays all project dependencies and then lists the repositories used.

```bash
    mvn dependency:list-repositories -P dev-local
```
### all dependencies and their source attachments, and displays the version.
```bash
    mvn dependency:sources  -P dev-local
```
