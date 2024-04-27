package com.eep.simarro.Drones;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DronesdApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnADronesWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/Drones/2", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();
        
        String name = documentContext.read("$.name");
        assertThat(name).isNotNull();
        
        String apellido = documentContext.read("$.apellido");
        assertThat(apellido).isNotNull();
        
        String DE = documentContext.read("$.DE");
        assertThat(DE).isNotNull();
        
    }
    @Test
    void shouldNotReturnADronesdWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/Drones/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
}