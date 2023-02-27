package com.github.aranciro.client.pojo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Builder
@Getter
public class APIServer {
    
    private static final String KEY_SERVER_HOST_DEFAULT = "server.host";
    private static final String KEY_SERVER_PORT_DEFAULT = "server.port";
    
    @NonNull
    private final String host;
    private final int port;
    
    public APIServer() {
        @NonNull Properties properties = this.loadProperties();
        this.host = properties.getProperty(KEY_SERVER_HOST_DEFAULT);
        this.port = Integer.parseInt(properties.getProperty(KEY_SERVER_PORT_DEFAULT));
    }
    
    public APIServer(@NonNull final String host) {
        @NonNull Properties properties = this.loadProperties();
        this.host = host;
        this.port = Integer.parseInt(properties.getProperty(KEY_SERVER_PORT_DEFAULT));
    }
    
    public APIServer(final int port) {
        @NonNull Properties properties = this.loadProperties();
        this.port = port;
        this.host = properties.getProperty(KEY_SERVER_HOST_DEFAULT);
    }
    
    public APIServer(@NonNull final String host, final int port) {
        this.host = host;
        this.port = port;
    }
    
    @NonNull
    private Properties loadProperties() {
        try {
            InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("client.properties");
            Properties props = new Properties();
            props.load(resourceStream);
            return props;
        } catch (IOException e) {
            // TODO change ex?
            throw new IllegalStateException("Unable to load client properties", e);
        }
    }
    
}
