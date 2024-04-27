package com.eep.simarro.Drones;

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

    @Test
    void estudiantesSerializationTest() throws IOException {
        Drones Drones = new Drones (1,"Paco", "Gonzalez", "HOVERAir X1");
        assertThat(json.write(Drones)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(Drones)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(Drones)).extractingJsonPathNumberValue("@.id")
            .isEqualTo(1);

        assertThat(json.write(Drones)).hasJsonPathStringValue("@.name");
        assertThat(json.write(Drones)).extractingJsonPathStringValue("@.name")
            .isEqualTo("Paco");

        assertThat(json.write(Drones)).hasJsonPathStringValue("@.apellido");
        assertThat(json.write(Drones)).extractingJsonPathStringValue("@.apellido")
            .isEqualTo("Gonzalez");

        assertThat(json.write(Drones)).hasJsonPathStringValue("@.DE");
        assertThat(json.write(Drones)).extractingJsonPathStringValue("@.DE")
             .isEqualTo("HOVERAir X1");
       
    }
    @Test
    void internsDeserializationTest() throws IOException {
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
       assertThat(json.parseObject(expected).id()).isEqualTo(2);
       assertThat(json.parseObject(expected).name()).isEqualTo("Mario");
       assertThat(json.parseObject(expected).apellido()).isEqualTo("Garcia");
       assertThat(json.parseObject(expected).DE()).isEqualTo("4DRC F11 PRO");
    }
}