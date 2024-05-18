package com.eep.simarro.Drones;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest 
class DronesJsonTest {

    @Autowired
    private JacksonTester<Drones> json;
    @Autowired
    private JacksonTester<Drones[]> jsonlist;

    private Drones[] dronesArray;

    @BeforeEach
    void setUp() {
        dronesArray = Arrays.array(
                new Drones(1, 1000, "negro", "HOVERAir X1"),
                new Drones(2, 1500, "rojo", "H4DRC F11 PRO"),
                new Drones(3, 2000, "azul", "CASC CH-92"));
    }
    
    @Test
    void dronesSerializationTest() throws IOException {
        Drones drone = new Drones(1, 1000, "negro", "HOVERAir X1");
        assertThat(json.write(drone)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(drone)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(drone)).extractingJsonPathNumberValue("@.id").isEqualTo(1);

        assertThat(json.write(drone)).hasJsonPathNumberValue("@.precio");
        assertThat(json.write(drone)).extractingJsonPathNumberValue("@.precio").isEqualTo(1000);

        assertThat(json.write(drone)).hasJsonPathStringValue("@.color");
        assertThat(json.write(drone)).extractingJsonPathStringValue("@.color").isEqualTo("negro");

        assertThat(json.write(drone)).hasJsonPathStringValue("@.DE");
        assertThat(json.write(drone)).extractingJsonPathStringValue("@.DE").isEqualTo("HOVERAir X1");
    }
    
    @Test
    void listDeserializationTest() throws IOException {
        assertThat(jsonlist.write(dronesArray)).isStrictlyEqualToJson("list.json");
    }
    
    @Test
    void dronesDeserializationTest() throws IOException {
        String expected = """
               {
                   "id":2,
                   "precio":1500,
                   "color":"rojo",
                   "DE":"H4DRC F11 PRO"
               }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new Drones(2, 1500, "rojo", "H4DRC F11 PRO"));
       assertThat(json.parseObject(expected).getId()).isEqualTo(2);
       assertThat(json.parseObject(expected).getPrecio()).isEqualTo(1500);
       assertThat(json.parseObject(expected).getColor()).isEqualTo("rojo");
       assertThat(json.parseObject(expected).getDE()).isEqualTo("H4DRC F11 PRO");
    }
    
    @Test
    void internsListDeserializationTest() throws IOException {
        String expected = """
                [
                    { "id":1, "precio":1000, "color":"negro", "DE":"HOVERAir X1" },
                    { "id":2, "precio":1500, "color":"rojo", "DE":"H4DRC F11 PRO" },
                    { "id":3, "precio":2000, "color":"azul", "DE":"CASC CH-92" }
                ]
                 """;
        assertThat(jsonlist.parse(expected)).isEqualTo(dronesArray);
    }
    
}
