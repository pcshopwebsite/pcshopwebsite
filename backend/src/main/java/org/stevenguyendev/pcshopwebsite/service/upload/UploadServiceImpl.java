package org.stevenguyendev.pcshopwebsite.service.upload;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.stevenguyendev.pcshopwebsite.exception.StorageException;
import org.stevenguyendev.pcshopwebsite.exception.StorageFileNotFoundException;
import org.stevenguyendev.pcshopwebsite.service.storage.StorageService;

import java.io.IOException;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    @Qualifier("fileSystemStorageService")
    StorageService storageService;
    @Override
    public String initializeUpload(long l, List<String> expectedOffsets) {
        return storageService.initTemp(l, expectedOffsets);
    }

    @Override
    public void uploadChunk(String locationId, long offset, long totalSize, String fileName, byte[] chunkData) throws IOException {
        storageService.storeTemp(locationId, offset, chunkData);
        if (storageService.allChunksUploaded(locationId)) {
            storageService.mergeTemp(locationId, fileName);
        }
    }

    @Override
    public long getNextExpectedOffset(String transferId) {
        return 0;
    }

    @Override
    public void finalizeUpload(String transferId, String fileName) {
        if (!storageService.allChunksUploaded(transferId)) {
            throw new StorageFileNotFoundException("Missing chunk to finalize the upload");
        }
        if (Strings.isEmpty(fileName)) {
            throw new StorageException("Name of file to be stored could not be empty");
        }
        try {
            storageService.mergeTemp(transferId, fileName);
        } catch (IOException e) {
            throw new StorageException(String.format("Failed to merge temporary files for upload =%s, file name =%s", transferId, fileName));
        }
    }
}
