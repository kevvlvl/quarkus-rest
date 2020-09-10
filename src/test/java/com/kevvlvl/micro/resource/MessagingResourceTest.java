package com.kevvlvl.micro.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevvlvl.micro.dto.MessageDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MessagingResourceTest {

    private MessageDto dto;
    private String dtoJson;

    @BeforeEach
    public void init() throws JsonProcessingException {
        dto = new MessageDto();
        dto.setMessage("message from quarkus test!");

        ObjectMapper mapper = new ObjectMapper();
        dtoJson = mapper.writeValueAsString(dto);
    }

    @Test
    public void testSendConsumeMessage() {

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dtoJson)
                .post("/message/send")
                .then()
                .statusCode(200)
                .body(containsString("CONSUMED"));
    }
}
