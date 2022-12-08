package com.example.springbootmultimongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;

import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.*;
import org.springframework.data.mongodb.core.MongoTemplate;


import static org.assertj.core.api.Assertions.assertThat;

//TODO: HERE
class ManualEmbeddedMongoDbIntegrationTest {
    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private static MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    private static final MongodStarter starter = MongodStarter.getDefaultInstance();


    @AfterAll
    public  static void clean()
    {
        try
        {
            mongodExecutable.stop();

        }
        catch(Exception e)
        {
            mongodExecutable = null;
        }
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27018;

        MongodConfig mongodConfig = MongodConfig
                .builder()
                //.version(Version.Main.PRODUCTION)
                .version(Version.Main.V3_4)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        //spring 2.7+
//        IMongodConfig mongodConfig = new MongodConfigBuilder()
//                .version(Version.Main.V3_4)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();


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