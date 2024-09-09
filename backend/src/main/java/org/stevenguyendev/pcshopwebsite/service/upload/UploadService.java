package org.stevenguyendev.pcshopwebsite.service.upload;

import java.io.IOException;
import java.util.List;

public interface UploadService {

    /** Initializes the upload and return unique storage ID. */
    public String initializeUpload(long l, List<String> expectedOffsets);

    public void uploadChunk(String transferId, long offset, long totalSize, String fileName, byte[] chunkData) throws IOException;

    public long getNextExpectedOffset(String transferId);

    public void finalizeUpload(String transferId, String fileName);
}
