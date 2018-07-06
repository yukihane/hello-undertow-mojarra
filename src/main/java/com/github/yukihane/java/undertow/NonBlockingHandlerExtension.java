package com.github.yukihane.java.undertow;

import javax.servlet.ServletContext;

import io.undertow.Handlers;
import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;
import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;

public class NonBlockingHandlerExtension implements ServletExtension {
    @Override
    public void handleDeployment(final DeploymentInfo deploymentInfo, final ServletContext servletContext) {
        deploymentInfo.addInitialHandlerChainWrapper(new HandlerWrapper() {
            @Override
            public HttpHandler wrap(final HttpHandler handler) {
                return Handlers.path()
                        .addPrefixPath("/", handler)
                        .addPrefixPath("/hello", new HelloWorldHandler());
            }
        });
    }
}