package jy.demo.exception;

import jy.demo.common.HttpResponse;
public class DataNotFoundException extends RuntimeException {

    private final HttpResponse httpResponse;

    public DataNotFoundException(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }
}