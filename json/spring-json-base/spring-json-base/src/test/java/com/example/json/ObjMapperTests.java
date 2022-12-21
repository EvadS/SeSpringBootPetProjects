package com.example.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

public class ObjMapperTests {
    @Test
    public void testGetAssetsByAssetName() throws JsonProcessingException {
        String content = "{ \"code\": \"32705_85401_210716125930\", \"values\": { \"label\": [ { \"locale\": \"en_US\", \"channel\": null, \"data\": \"32705-85401-210716125930\" } ], \"position\": [ { \"locale\": null, \"channel\": null, \"data\": \"1\" } ], \"primary\": [ { \"locale\": null, \"channel\": null, \"data\": \"Y\" } ], \"link\": [ { \"locale\": null, \"channel\": null, \"data\": \"https://cdn3.1800flowers.com/wcsstore/PersonalizationMall/images/catalog//32705-85401-210716125930x.jpg\" } ], \"media_type\": [ { \"locale\": null, \"channel\": null, \"data\": \"image\" } ] } }";
//        doReturn(new ObjectMapper().readTree(content)).when(pimAssetClient).getAssetData("32705_85401_210716125930");
//        Image options = pimAssetService.getAssetDataAsImage("32705_85401_210716125930");
//        assertNotNull(options);
        JsonNode jsonNode = new ObjectMapper().readTree(content);

        int a =0;

    }
}
