package com.bitpanda.homework.automation.test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.tools.classfile.ConstantPool;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class ApiTest {

    @Test
    public void shouldReturnStatus401() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("http://www.mocky.io/v2/5e7fab532f00003f57bac525")
                .header("accept", "application/json").queryString("apiKey", "123")
                .asJson();

        assertNotNull(jsonResponse.getBody());
        assertEquals(HttpStatus.SC_UNAUTHORIZED, jsonResponse.getStatus());

    }

    @Test
    public void shouldReturnStatus200() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("http://www.mocky.io/v2/5e7fab072f00007c00bac522")
                .header("accept", "application/json").queryString("apiKey", "123")
                .asJson();

        assertNotNull(jsonResponse.getBody());
        assertEquals(HttpStatus.SC_OK, jsonResponse.getStatus());
    }

    @Test
    public void shouldReturnStatus304() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("http://www.mocky.io/v2/5e7fab282f00005a4cbac523")
                .header("accept", "application/json").queryString("apiKey", "123")
                .asJson();

        assertNull(jsonResponse.getBody());
        assertEquals(HttpStatus.SC_NOT_MODIFIED, jsonResponse.getStatus());
    }


}
