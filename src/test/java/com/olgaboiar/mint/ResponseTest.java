package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseTest {
    String currentDate;
    String date;
    Header header;

    @BeforeEach
    public void init(){
        currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
        date = "Date: " + currentDate;
        header = new Header("200 OK", "Content-Type: text/html", date);
    }

    @Test
    void responseHasBlankLineWhenBodyIsAdded() {
        Body body = new Body("body");
        Response testResponse = new Response (header, body);
        String actual = testResponse.prepareResponse();
        String expected = "200 OK\n" +
                "Content-Type: text/html\n" +
                "Date: " + currentDate + "\n" +
                "\n" +
                "body";

        assertEquals(expected, actual);
    }

    @Test
    void responseHasNoBlankLineWithoutBody() {
        Body body = new Body("");
        Response testResponse = new Response (header, body);
        String actual = testResponse.prepareResponse();
        String expected = "200 OK\n" +
                "Content-Type: text/html\n" +
                "Date: " + currentDate;

        assertEquals(expected, actual);
    }

}
