package com.example.springbootmultimongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;


import static org.assertj.core.api.Assertions.assertThat;

//TODO: HERE
class ManualEmbeddedMongoDbIntegrationTest {
    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    private static final MongodStarter starter = MongodStarter.getDefaultInstance();


    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27018;

//        IMongodConfig mongodConfig = MongodConfig
//                .builder()
//                //.version(Version.Main.PRODUCTION)
//                .version(Version.Main.V3_4)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.V3_4)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();


        mongodExecutable = null;


        try
        {
            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();

            mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");

        }
        catch(Exception e)
        {
            // log exception here
            if (mongodExecutable != null)
                mongodExecutable.stop();
        }
    }

    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    void test() throws Exception {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");
    }
}