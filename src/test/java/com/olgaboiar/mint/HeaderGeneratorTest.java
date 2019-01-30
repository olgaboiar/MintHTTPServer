package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class HeaderGeneratorTest {

    @Test
    public void testGeneratesCorrectStatusLine() {
        String statusCode = "200 OK";

        HeaderGenerator generator = new HeaderGenerator();
        Header header = generator.generate(statusCode, "");

        assertEquals("HTTP/1.1 200 OK", header.getStatusLine());
    }

    @Test
    public void testGeneratesCorrectDate() {
        String statusCode = "200 OK";
        String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());;

        HeaderGenerator generator = new HeaderGenerator();
        Header header = generator.generate(statusCode, "");

        assertEquals("Date: " + date, header.getDate());
    }
}
