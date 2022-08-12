package com.se.sample;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.base.Item;
import com.se.sample.model.base.User;
import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Evgeniy Skiba on 19.03.21
 */
public class JacksonBidirectionRelationUnitTest {

    @Test(expected = JsonMappingException.class)
    public void givenBidirectionRelation_whenSerializing_thenException() throws JsonProcessingException {
        final User user = new User(1, "John");
        final Item item = new Item(2, "book", user);
        user.addItem(item);

        String s = new ObjectMapper().writeValueAsString(item);
        System.out.println("s: "+ s);
    }

    @Test
    public  void keep_working() throws JsonProcessingException {

        final User user = new User(1, "John");
        final Item item = new Item(2, "book", user);

        String json = new ObjectMapper().writeValueAsString(item);
        Item itemWithOwner = new ObjectMapper().readValue(json, Item.class);
        int a =0;

    }
}
