package org.stevenguyendev.pcshopwebsite.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.controller.requests.FinalizeRequest;
import org.stevenguyendev.pcshopwebsite.controller.requests.UploadRequest;
import org.stevenguyendev.pcshopwebsite.service.storage.StorageService;
import org.stevenguyendev.pcshopwebsite.service.upload.UploadService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@AllArgsConstructor
@CrossOrigin(
    exposedHeaders = "*"
)
public class UploadController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

//    @Autowired
    private final UploadService uploadService;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * FilePond requests a transfer id from the server, a unique location to identify this transfer with. It does this
     * using a POST request. The request is accompanied by the metadata and the total file upload size set to the
     * Upload-Length header.
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    @PostMapping(
            value = "/process",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> initializeUpload(
            @RequestHeader("Upload-Length") long totalSize,
            @RequestParam("filepond") String filePondAttributes
    ) throws JsonMappingException, JsonProcessingException {
        UploadRequest request = mapper.readValue(filePondAttributes, UploadRequest.class);
        String locationId = uploadService.initializeUpload(totalSize, request.expectedOffsets());
        return ResponseEntity.ok(locationId);
    }

    @RequestMapping(
            consumes = "application/offset+octet-stream",
            method = {
                RequestMethod.PATCH
            }
    )
    public ResponseEntity<Void> uploadChunk(@RequestParam("patch") String locationId,
                                            @RequestHeader("Upload-Offset") long offset,
                                            @RequestHeader("Upload-Length") long totalSize,
                                            @RequestHeader("Upload-Name") String fileName,
                                            @RequestBody byte[] chunkData
    ) throws IOException {
        LOGGER.info("uploadChunk: locationId={}, offset={}, totalSize={}, fileName={}, chunk size={}",
                locationId, offset, totalSize, fileName, chunkData.length);
        uploadService.uploadChunk(locationId, offset, totalSize, fileName, chunkData);
        // if (totalSize <= offset + chunkData.length) {
        //     uploadService.finalizeUpload(locationId, fileName);
        // }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            method = {
                RequestMethod.HEAD
            }
    )
    public ResponseEntity<Void> checkUploadedChunks(@RequestParam("patch") String transferId) {
        long nextOffset = uploadService.getNextExpectedOffset(transferId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Upload-Offset", String.valueOf(nextOffset));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    // @PostMapping("/finalize")
    // public ResponseEntity<Void> finalizeUpload(@RequestBody FinalizeRequest request) {
    //     uploadService.finalizeUpload(request.transferId());
    //     return ResponseEntity.ok().build();
    // }
}
