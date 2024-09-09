package org.stevenguyendev.pcshopwebsite.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();
    String initTemp(long fileSize, List<String> expectedOffsets);

    void store(MultipartFile file);
    void storeTemp(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void storeTemp(String locationId, long offset, byte[] chunkData);

    void mergeTemp(String locationId, String fileName) throws IOException;
    boolean allChunksUploaded(String locationId);
}