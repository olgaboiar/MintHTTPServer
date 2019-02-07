package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class HeaderTest {

    @Test
    void getStatusLineReturnsStatusLine() {
        String code200 = "200 OK";
        String contentType = ("Content-Type: text/html");
        String currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
        String date = "Date: " + currentDate;
        Header testHeader = new Header(code200,contentType, date);

        assertEquals(code200, testHeader.getStatusLine());
    }

    @Test
    void getContentTypeReturnsContentType() {
        String code200 = "200 OK";
        String contentType = ("Content-Type: text/html");
        String currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
        String date = "Date: " + currentDate;
        Header testHeader = new Header(code200,contentType, date);

        assertEquals(contentType, testHeader.getContentType());
    }
}

//    String getDate() {
//        return date;
//    }
//
//    public ArrayList<String> getHeaders() {
//        ArrayList<String> headers = new ArrayList<String>();
//        headers.add(getStatusLine());
//        headers.add(getContentType());
//        headers.add(getDate());
//        if (allowedMethodsExist()) {
//            String aloowHeader = createAllowHeader();
//            headers.add(aloowHeader);
//        }
//        if (redirectExist()) {
//            String redirectHeader = createRedirectHeader();
//            headers.add(redirectHeader);
//        }
//        return headers;
//    }
//
//    public String prepareHeaders(ArrayList<String> headers) {
//        String headersToSend = String.join("\n", headers);
//        headersToSend += BLANK_LINE;
//        return headersToSend;
//    }
//
//    private boolean allowedMethodsExist () {
//        if (allowedMethods != null) {
//            return true;
//        }
//        return false;
//    }
//
//    private String createAllowHeader() {
//        String allMethods = String.join(",", allowedMethods);
//        String allowHeader = "Allow: " + allMethods;
//        return allowHeader;
//
//    }
//
//    private String createRedirectHeader () {
//        String redirectHeader = "Location: " + redirect;
//        return redirectHeader;
//    }
//
//    private boolean redirectExist () {
//        if (redirect != null) {
//            return true;
//        }
//        return false;
//    }
//
//    public void setAllowMethods(ArrayList<String> methods) {
//        allowedMethods = methods;
//    }
//
//    public void setRedirection(String redirectTarget) {
//        redirect = redirectTarget;
//    }
//}
