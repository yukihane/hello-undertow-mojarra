package com.github.yukihane.java.undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class HelloWorldServer {

    public static void main(String[] args) {
        Undertow server = Undertow.builder().addHttpListener(8080, "localhost")
                .setHandler((HttpServerExchange exchange) -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello, world!");
                }).build();

        server.start();
    }
}
