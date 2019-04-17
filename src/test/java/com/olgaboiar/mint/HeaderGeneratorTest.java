package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class HeaderGeneratorTest {
    Request testRequest;

    @BeforeEach
    public void init() throws MalformedURLException {
        testRequest = new Request(new URL("http://0.0.0.0:5000/method_options"), "GET", "Content-Type: text/html");
    }

    @Test
    public void testGeneratesCorrectStatusLine() {
        HeaderGenerator generator = new HeaderGenerator();
        Header header = generator.generate(Constants.Status.STATUS_CODE_200);

        assertEquals("HTTP/1.1 200 OK", header.getStatusLine());
    }

    @Test
    public void testGeneratesCorrectDate() {
        String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());;

        HeaderGenerator generator = new HeaderGenerator();
        Header header = generator.generate(Constants.Status.STATUS_CODE_200);

        assertEquals("Date: " + date, header.getDate());
    }
}
