
# Configuring IntelliJ IDEA for Kafka


## base article link 
https://www.learningjournal.guru/article/kafka/configuring-intellij-idea-for-kafka/


## run configuration 

Program arguments: test 10
```
    $Env:KAFKA_HOME
```
## How to use scripts 
set up KAFKA_HOME 

To check on win 11
echo $Env:PATH

install a plugin to support the CMD files as shown in the below figure. You must choose to Install the plugin.
Then select Run ‘start-zookeeper’ from the menu item

## Run the next script from project scripts folder
 Right mouse click on intellij -> Run 
 * start-zookeeper.cmd
 * start-kafka-server.cmd
 * start-console-consumer.cmd