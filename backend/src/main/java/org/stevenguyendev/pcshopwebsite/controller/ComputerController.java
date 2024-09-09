package org.stevenguyendev.pcshopwebsite.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.ComputerDTO;
import org.stevenguyendev.pcshopwebsite.dto.ComputerLiteDTO;
import org.stevenguyendev.pcshopwebsite.dto.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.dto.ComputerSort;
import org.stevenguyendev.pcshopwebsite.service.ComputerService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/computers")
@CrossOrigin("*")
@AllArgsConstructor
public class ComputerController extends BaseController {
    private final ComputerService computerService;

    @GetMapping
    public ResponseEntity<Collection<ComputerLiteDTO>> getAllComputers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false, defaultValue = "updatedAt") String sort,
            @RequestParam(required = false, defaultValue = "desc") String order
    ) {
        /*
          Make sure the sort value is valid, otherwise set it to default value
         */
        if (sort != null) {
            try {
                ComputerSort.valueOf(sort.toUpperCase());
            } catch (IllegalArgumentException e) {
                sort = "updatedAt";
            }
        }
        ComputerFilterRequest filterRequest = new ComputerFilterRequest(
                name,
                categories,
                brands,
                minPrice,
                maxPrice,
                minRating,
                sort,
                order,
                page,
                size
        );
        return ResponseEntity.ok(computerService.filterComputers(filterRequest));
    }

    @GetMapping("/{idOrName}")
    public ResponseEntity<ComputerDTO> getComputerByIdOrName(@PathVariable String idOrName) {
        return ResponseEntity.ok(computerService.findComputerByIdOrName(idOrName));
    }

    @PostMapping
    public ResponseEntity<ComputerDTO> createComputer(@RequestBody ComputerDTO createRequest) {
        return ResponseEntity.ok(computerService.createComputer(createRequest));
    }
}
