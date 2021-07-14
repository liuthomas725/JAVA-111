package org.geektimes.configuration.microprofile.config.source.servlet;

import org.geektimes.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

public class ServletRequestConfigSource extends MapBasedConfigSource {

    private final ServletRequest servletRequest;

    public ServletRequestConfigSource(ServletRequest servletRequest) {
        super(String.valueOf(servletRequest.getAttributeNames().hashCode()), 700);
        this.servletRequest = servletRequest;
    }


    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        configData.putAll(Collections.unmodifiableMap(servletRequest.getParameterMap()));
    }
}
