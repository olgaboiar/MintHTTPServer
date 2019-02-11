package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HeaderTest {
    Header testHeader;
    String code200 = "200 OK";
    String contentType = ("Content-Type: text/html");
    String currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
    String date = "Date: " + currentDate;

    @BeforeEach
    public void init(){
        testHeader = new Header(code200,contentType, date);
    }

    @Test
    void getStatusLineReturnsStatusLine() {
        assertEquals(code200, testHeader.getStatusLine());
    }

    @Test
    void getContentTypeReturnsContentType() {
        assertEquals(contentType, testHeader.getContentType());
    }

    @Test
    void getDateReturnsDate() {
        assertEquals(date, testHeader.getDate());
    }

    @Test
    void getHeadersReturnsHeaderArrayWithoutRedirectAndAllowedMethods() {
        ArrayList<String> headers = new ArrayList<String>();
        headers.add(code200);
        headers.add(contentType);
        headers.add(date);
        ArrayList<String> actual = testHeader.getHeaders();

        assertEquals(headers, actual);
    }

    @Test
    void getHeadersReturnsHeaderArrayWithRedirect() {
        testHeader.setRedirection("http://test.com");
        ArrayList<String> headers = new ArrayList<String>();
        headers.add(code200);
        headers.add(contentType);
        headers.add(date);
        headers.add("Location: http://test.com");
        ArrayList<String> actual = testHeader.getHeaders();

        assertEquals(headers, actual);
    }

    @Test
    void getHeadersReturnsHeaderArrayWithAllowedMethods() {
        ArrayList<String> methods = new ArrayList<String>();
        methods.add("GET");
        testHeader.setAllowMethods(methods);
        ArrayList<String> headers = new ArrayList<String>();
        headers.add(code200);
        headers.add(contentType);
        headers.add(date);
        headers.add("Allow: GET");
        ArrayList<String> actual = testHeader.getHeaders();

        assertEquals(headers, actual);
    }

    @Test
    void returnsFalseWhenNoAllowedMethodsAreSet() {
        Boolean actual = testHeader.allowedMethodsExist();

        assertFalse(actual);
    }

    @Test
    void returnsTrueWhenAllowedMethodsAreSet() {
        ArrayList<String> methods = new ArrayList<String>();
        methods.add("GET");
        testHeader.setAllowMethods(methods);
        Boolean actual = testHeader.allowedMethodsExist();

        assertTrue(actual);
    }

    @Test
    void returnsFalseWhenNoRedirectIsSet() {
        Boolean actual = testHeader.redirectExist();

        assertFalse(actual);
    }

    @Test
    void returnsTrueWhenRedirectIsSet() {
        testHeader.setRedirection("http://test.com");
        Boolean actual = testHeader.redirectExist();

        assertTrue(actual);
    }

    @Test
    void allowHeaderIsGeneratedCorrectly() {
        ArrayList<String> methods = new ArrayList<String>();
        methods.add("GET");
        methods.add("HEAD");
        testHeader.setAllowMethods(methods);
        String actual = testHeader.createAllowHeader();
        String expected = "Allow: GET,HEAD";

        assertEquals(expected, actual);
    }

    @Test
    void redirectHeaderIsGeneratedCorrectly() {
        testHeader.setRedirection("http://test.com");
        String actual = testHeader.createRedirectHeader();
        String expected = "Location: http://test.com";

        assertEquals(expected, actual);
    }

    @Test
    void setsRedirection() {
        testHeader.setRedirection("http://test.com");
        Boolean actual = testHeader.redirectExist();

        assertTrue(actual);
    }

    @Test
    void setsAllowedMethods() {
        ArrayList<String> methods = new ArrayList<String>();
        methods.add("GET");
        methods.add("HEAD");
        testHeader.setAllowMethods(methods);
        Boolean actual = testHeader.allowedMethodsExist();

        assertTrue(actual);
    }

    @Test
    void returnsStringOfHeaders() {
        ArrayList<String> headers = testHeader.getHeaders();
        String actual = testHeader.prepareHeaders();
        String expected = "200 OK\n" +
                "Content-Type: text/html\n" +
                testHeader.getDate();

        assertEquals(expected, actual);
    }
}
