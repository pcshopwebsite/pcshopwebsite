package org.stevenguyendev.pcshopwebsite.controller.requests;

import java.util.List;

public record UploadRequest(List<String> expectedOffsets) {

}
