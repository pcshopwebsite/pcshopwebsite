package org.stevenguyendev.pcshopwebsite.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(
        prefix = "storage"
)
@Getter
@Setter
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location;
    private String tempLocation;

}