package com.github.yukihane.java.undertow;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletException;

import com.sun.faces.RIConstants;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;

public class HelloWorldServer {

    public static void main(String[] args) throws ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(HelloWorldServer.class.getClassLoader())
                .setContextPath("/myapp")
                .setDeploymentName("test.war")
                .addServlets(
                        Servlets.servlet("FacesServlet", FacesServlet.class)
                                .addMappings("/faces/*", "*.jsf", "*.faces", "*.xhtml")
                                .setLoadOnStartup(1));
        servletBuilder.addServletContextAttribute(RIConstants.FACES_INITIALIZER_MAPPINGS_ADDED, Boolean.TRUE);

//        servletBuilder.addServletContextAttribute("com.sun.faces.forceLoadConfiguration", Boolean.TRUE);
//        servletBuilder.addServletContextAttribute("com.sun.faces.expressionFactory",
//                "com.sun.el.ExpressionFactoryImpl");
//        servletBuilder.addInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");

        servletBuilder.addListener(new ListenerInfo(com.sun.faces.config.ConfigureListener.class));
        servletBuilder
                .setResourceManager(new ClassPathResourceManager(HelloWorldServer.class.getClassLoader(), "static"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        PathHandler path = Handlers.path(Handlers.redirect("/myapp"))
                .addPrefixPath("/myapp", manager.start());

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(path)
                .build();
        server.start();
    }
}
