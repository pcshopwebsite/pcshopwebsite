package org.stevenguyendev.pcshopwebsite.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.stevenguyendev.pcshopwebsite.configuration.StorageProperties;
import org.stevenguyendev.pcshopwebsite.exception.StorageException;
import org.stevenguyendev.pcshopwebsite.exception.StorageFileNotFoundException;

@Service("fileSystemStorageService")
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final Path tempLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {

        if(properties.getLocation().trim().length() == 0){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(properties.getLocation());
        this.tempLocation = Paths.get(properties.getTempLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void storeTemp(MultipartFile file) {

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void storeTemp(String locationId, long offset, byte[] chunkData) {
        Path tempLocation = this.tempLocation.resolve(locationId);
//        try {
//            Files.createDirectories(tempLocation);
//        }
//        catch (IOException e) {
//            throw new StorageException("Could not initialize storage", e);
//        }
        Path tempFile = tempLocation.resolve(offset + ".tmp");
        try {
            Files.write(tempFile, chunkData);
        }
        catch (IOException e) {
            throw new StorageException("Could not store temporary file", e);
        }
    }

    @Override
    public void mergeTemp(String locationId, String fileName) throws IOException {
        Path tempDir = tempLocation.resolve(locationId);
        List<Path> chunkPaths = getChunkPaths(tempDir);
        Path targetFile = rootLocation.resolve(fileName);
        Files.createFile(targetFile);
        try (var outputStream = Files.newOutputStream(targetFile)) {
            for (Path chunkPath : chunkPaths) {
                Files.copy(chunkPath, outputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean allChunksUploaded(String locationId) {
        Path uploadChunksPath = tempLocation.resolve(locationId);
        Path metadataFilePath = uploadChunksPath.resolve("meta.dat");
        if (Files.exists(metadataFilePath)) {
            try (var inputStream = Files.newInputStream(metadataFilePath)) {
                String metadata = new String(inputStream.readAllBytes());
                String[] expectedOffsets = metadata.split("\n");
                if (Files.walk(uploadChunksPath).count() != expectedOffsets.length) {
                    return false;
                }
                for (String offset : expectedOffsets) {
                    if (!Files.exists(uploadChunksPath.resolve(offset + ".tmp"))) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private List<Path> getChunkPaths(Path tempDir) throws IOException {
        return Files.walk(tempDir)
                .filter(Files::isRegularFile)
                .filter(path -> {
                    String fileName = path.getFileName().toString();
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                    return "tmp".equals(extension);
                })
                .sorted((path1, path2) -> {
                    String fileName1 = path1.getFileName().toString();
                    String fileName2 = path2.getFileName().toString();
                    String offsetString1 = fileName1.substring(0, fileName1.lastIndexOf("."));
                    String offsetString2 = fileName2.substring(0, fileName2.lastIndexOf("."));
                    long offset1 = Long.parseLong(offsetString1);
                    long offset2 = Long.parseLong(offsetString2);
                    return Long.compare(offset1, offset2);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(tempLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String initTemp(long fileSize, List<String> expectedOffsets) {
        String locationId = UUID.randomUUID().toString();
        // TODO check free space
        Path tempUploadLocation = this.tempLocation.resolve(locationId);
        try {
            Files.createDirectories(tempUploadLocation);
            Path metadataFile = tempUploadLocation.resolve("meta.dat");
            Files.write(metadataFile, expectedOffsets.stream().map(offset -> offset + "\n").collect(Collectors.toList()));
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
        return locationId;
    }
}