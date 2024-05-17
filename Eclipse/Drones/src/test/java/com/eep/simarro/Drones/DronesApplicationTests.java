//package com.eep.simarro.Drones;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.net.URI;
//import com.jayway.jsonpath.DocumentContext;
//import com.jayway.jsonpath.JsonPath;
//import net.minidev.json.JSONArray;
//
////@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class DronesdApplicationTests {
//    @Autowired
//    TestRestTemplate restTemplate;
//
//    @Test
//    void shouldReturnADronesWhenDataIsSaved() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/drones/1", String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        Number id = documentContext.read("$.id");
//        assertThat(id).isEqualTo(1);
//        
//        String name = documentContext.read("$.name");
//        assertThat(name).isEqualTo("Paco");
//        
//        String apellido = documentContext.read("$.apellido");
//        assertThat(apellido).isEqualTo("Gonzalez");
//        
//        String DE = documentContext.read("$.DE");
//        assertThat(DE).isEqualTo("HOVERAir X1");
//        
//    }
//    @Test
//    void shouldNotReturnADronesdWithAnUnknownId() {
//      ResponseEntity<String> response = restTemplate.getForEntity("/Drones/1000", String.class);
//
//      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//      assertThat(response.getBody()).isBlank();
//      
//    }
//    @Test
//    @DirtiesContext
//    void shouldCreateANewDron() {
//       Drones newDrones = new Drones(2,"Paco","Gonzalez","HOVERAir X1");  
//       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/Drones", newDrones, Void.class);
//       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//       URI locationOfNewDrones = createResponse.getHeaders().getLocation();
//       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewDrones, String.class);
//       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//      
//
//    DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//    Number id = documentContext.read("$.id");
//    assertThat(id).isEqualTo(2);
//    
//    String name = documentContext.read("$.name");
//    assertThat(name).isEqualTo("Paco");
//    
//    String apellido = documentContext.read("$.apellido");
//    assertThat(apellido).isEqualTo("Gonzalez");
//    
//    String DE = documentContext.read("$.DE");
//    assertThat(DE).isEqualTo("HOVERAir X1");
//    
//    }
//    @Test
//    void shouldReturnAllDronesWhenListIsRequested() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/Drones", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        int dronesCount = documentContext.read("$.length()");
//        assertThat(dronesCount).isEqualTo(3);
//
//        JSONArray id = documentContext.read("$..id");
//        assertThat(id).containsExactlyInAnyOrder(1, 2, 3);
//
//        JSONArray name = documentContext.read("$..name");
//        assertThat(name).containsExactlyInAnyOrder("Paco","Mario","Carlos");
//        
//        JSONArray apellido = documentContext.read("$..apellido");
//        assertThat(apellido).containsExactlyInAnyOrder("Gonzalez","Garcia","Lopez");
//        
//        JSONArray DE = documentContext.read("$..DE");
//        assertThat(DE).containsExactlyInAnyOrder("HOVERAir X1","H4DRC F11 PRO","CASC CH-92");
//    }
//    @Test
//    void shouldReturnAPageOfDrones() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/Drones?page=0&size=1", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        JSONArray page = documentContext.read("$[*]");
//        assertThat(page.size()).isEqualTo(1);
//    }
//    @Test
//    void shouldReturnASortedPageOfDrones() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/Drones?page=0&size=1&sort=id,asc", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        JSONArray read = documentContext.read("$[*]");
//        assertThat(read.size()).isEqualTo(1);
//
//        int id = documentContext.read("$[0].id");
//        assertThat(id).isEqualTo(1);
//    }
//    @Test
//    void shouldReturnASortedPageOfDronesWithNoParametersAndUseDefaultValues() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/Drones", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        JSONArray page = documentContext.read("$[*]");
//        assertThat(page.size()).isEqualTo(3);
//
//        JSONArray id = documentContext.read("$..id");
//        assertThat(id).containsExactlyInAnyOrder(1, 2, 3);
//
//        JSONArray name = documentContext.read("$..name");
//        assertThat(name).containsExactlyInAnyOrder("Paco","Mario","Carlos");
//        
//        JSONArray apellido = documentContext.read("$..apellido");
//        assertThat(apellido).containsExactlyInAnyOrder("Gonzalez","Garcia","Lopez");
//        
//        JSONArray DE = documentContext.read("$..DE");
//        assertThat(DE).containsExactlyInAnyOrder("HOVERAir X1","H4DRC F11 PRO","CASC CH-92");
//    }
//    @Test
//    @DirtiesContext
//    void shouldUpdateAnExistingDrones() {
//        // Define los valores de ejemplo para la actualización
//        Drones dronesUpdate = new Drones(1, "Paco", "Gonzalez", "HOVERAir X1");
//
//        // Realiza la solicitud PUT para actualizar el objeto Drones
//        HttpEntity<Drones> request = new HttpEntity<>(dronesUpdate);
//        ResponseEntity<Void> response = restTemplate
//                .exchange("/Drones/1", HttpMethod.PUT, request, Void.class);
//
//        // Verifica que la actualización fue exitosa
//
//        // Verifica que los campos actualizados sean los esperados
//        ResponseEntity<Drones> getResponse = restTemplate
//                .getForEntity("/Drones/1", Drones.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Drones updatedDrones = getResponse.getBody();
//        assertThat(updatedDrones.getId()).isEqualTo(1);
//        assertThat(updatedDrones.getName()).isEqualTo("Paco");
//        assertThat(updatedDrones.getApellido()).isEqualTo("Gonzalez");
//        assertThat(updatedDrones.getDE()).isEqualTo("HOVERAir X1");
//
//    }
//    
//}
//
