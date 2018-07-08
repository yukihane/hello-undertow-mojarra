package com.github.yukihane.java.undertow.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletContext;

import com.sun.faces.spi.FacesConfigResourceProvider;

public class ClassPathProvider implements FacesConfigResourceProvider {

    @Override
    public Collection<URI> getResources(ServletContext context) {
        try {
            URL url = ClassPathProvider.class.getResource("/");
            return Arrays.asList(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
