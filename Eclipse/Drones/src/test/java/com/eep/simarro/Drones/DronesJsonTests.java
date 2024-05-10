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

    private Drones[] Drones;

    @BeforeEach
    void setUp() {
        Drones = Arrays.array(
                new Drones(1,"Paco", "Gonzalez", "HOVERAir X1"),
                new Drones(2,"Mario", "Garcia", "H4DRC F11 PRO"),
                new Drones(3,"Carlos", "Lopez", "CASC CH-92"));
    }
    @Test
    void DronesSerializationTest() throws IOException {
        Drones Dron = new Drones (1,"Paco", "Gonzalez", "HOVERAir X1");
        assertThat(json.write(Dron)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(Dron)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(Dron)).extractingJsonPathNumberValue("@.id")
            .isEqualTo(1);

        assertThat(json.write(Dron)).hasJsonPathStringValue("@.name");
        assertThat(json.write(Dron)).extractingJsonPathStringValue("@.name")
            .isEqualTo("Paco");

        assertThat(json.write(Dron)).hasJsonPathStringValue("@.apellido");
        assertThat(json.write(Dron)).extractingJsonPathStringValue("@.apellido")
            .isEqualTo("Gonzalez");

        assertThat(json.write(Dron)).hasJsonPathStringValue("@.DE");
        assertThat(json.write(Dron)).extractingJsonPathStringValue("@.DE")
             .isEqualTo("HOVERAir X1");
    }
    @Test
    void ListDeserializationTest() throws IOException {
    	 assertThat(jsonlist.write(Drones)).isStrictlyEqualToJson("list.json");
    }
    
    @Test
    void DronesDeserializationTest() throws IOException {
       String expected = """
               {
                   "id":2,
                   "name":"Mario",
                   "apellido":"Garcia",
                   "DE":"4DRC F11 PRO"
               }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new Drones(2,"Mario", "Garcia", "4DRC F11 PRO"));
       assertThat(json.parseObject(expected).getId()).isEqualTo(2);
       assertThat(json.parseObject(expected).getName()).isEqualTo("Mario");
       assertThat(json.parseObject(expected).getApellido()).isEqualTo("Garcia");
       assertThat(json.parseObject(expected).getDE()).isEqualTo("4DRC F11 PRO");
    }
    @Test
    void internsListDeserializationTest() throws IOException {
        String expected="""
                [
                    { "id":1, "name":"Paco", "apellido":"Gonzalez", "DE":"HOVERAir X1" },
                    { "id":2, "name":"Mario", "apellido":"Garcia", "DE":"H4DRC F11 PRO" },
                    { "id":3, "name":"Carlos", "apellido":"Lopez", "DE":"CASC CH-92" }
                ]
                 """;
           assertThat(jsonlist.parse(expected)).isEqualTo(Drones);
    }
    
}