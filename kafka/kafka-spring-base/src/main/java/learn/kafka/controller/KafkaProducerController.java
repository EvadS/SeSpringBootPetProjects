package learn.kafka.controller;


import learn.kafka.model.User;
import learn.kafka.service.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {
    private KafkaProducer kafkaProducer;

    public KafkaProducerController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

//    @GetMapping("/publish")
//    public ResponseEntity<String> publish(@RequestParam("message") String message){
//        kafkaProducer.sendMessage(message);
//        return ResponseEntity.ok("Message sent to kafka topic");
//    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
        kafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Message sent to kafka topic");
    }

    @GetMapping("/publish2")
    public ResponseEntity<String> publish(){
        User user = new User();
        user.setId(9999);
        user.setFirstName("FIRST NAME");
        user.setLastName("LAST NAME");

        kafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}