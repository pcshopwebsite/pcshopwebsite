package org.stevenguyendev.pcshopwebsite.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stevenguyendev.pcshopwebsite.dto.*;
import org.stevenguyendev.pcshopwebsite.dto.mapper.*;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;
import org.stevenguyendev.pcshopwebsite.model.Brand;
import org.stevenguyendev.pcshopwebsite.model.Category;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.repository.BrandRepository;
import org.stevenguyendev.pcshopwebsite.repository.CategoryRepository;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {
    private ComputerService underTest;
    @Mock
    private ComputerRepository computerRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock

    private CategoryRepository categoryRepository;
    private ComputerDTOMapper computerDTOMapper;
    private ComputerLiteDTOMapper computerLiteDTOMapper;

    @BeforeEach
    void beforeEach() {
        computerDTOMapper = new ComputerDTOMapper(
                new BrandDTOMapper(),
                new CategoryDTOMapper(),
                new MediaDTOMapper()
        );
        computerLiteDTOMapper = new ComputerLiteDTOMapper();
        underTest = new ComputerServiceImpl(
                computerRepository,
                brandRepository,
                categoryRepository,
                computerDTOMapper,
                computerLiteDTOMapper
        );
    }

    @Test
    void whenGetFilteredAndSortedComputers_thenCallComputerDAOGetFilteredAndSortedComputers() {
        ComputerFilterRequest request = new ComputerFilterRequest(
                "Macbook",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0,
                10
        );
        when(computerRepository.getFilteredAndSortedComputers(
                request.name(),
                request.brands(),
                request.categories(),
                null,
                null,
                request.minRating(),
                request.sortBy(),
                request.sortOrder(),
                request.pageNumber(),
                request.pageSize()
        )).thenReturn(List.of(
                new Computer(
                        UUID.randomUUID(),
                        "Macbook Pro Retina",
                        "Macbook Pro Retina MF839LL/A 13.3-Inch Laptop with Retina Display (2.7 GHz Intel Core i5 Processor, 8 GB RAM, 128 GB Hard Drive, OS X Yosemite)",
                        new BigDecimal("1299.99"),
                        4.5f,
                        "https://stevenguyendev.com/images/macbook-pro-retina.jpg",
                        new Category(
                                "Macbook"
                        ),
                        new Brand(
                                "Apple"
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new Computer(
                        UUID.randomUUID(),
                        "Macbook Air M1",
                        "Macbook Air M1 13.3-Inch Laptop with Retina Display (2.7 GHz Intel Core i5 Processor, 8 GB RAM, 128 GB Hard Drive, OS X Yosemite)",
                        new BigDecimal("1299.99"),
                        4.0f,
                        "https://stevenguyendev.com/images/macbook-air-m1.jpg",
                        new Category(
                                "Macbook"
                        ),
                        new Brand(
                                "Apple"
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        ));

        List<ComputerLiteDTO> computers = underTest.filterComputers(request);
        assertThat(computers).hasSize(2);
        assertThat(computers.get(0).name()).isEqualTo("Macbook Pro Retina");
        assertThat(computers.get(1).name()).isEqualTo("Macbook Air M1");

        ComputerFilterRequest request1 = new ComputerFilterRequest(
                "Macbook",
                null,
                null,
                600.00,
                700.00,
                null,
                null,
                null,
                0,
                10
        );
        when(computerRepository.getFilteredAndSortedComputers(
                request1.name(),
                request1.brands(),
                request1.categories(),
                new BigDecimal(request1.minPrice()),
                new BigDecimal(request1.maxPrice()),
                request1.minRating(),
                request1.sortBy(),
                request1.sortOrder(),
                request1.pageNumber(),
                request1.pageSize()
        )).thenReturn(List.of());

        List<ComputerLiteDTO> computers1 = underTest.filterComputers(request1);
        assertThat(computers1).hasSize(0);
    }

    @Test
    void givenComputerWithMatchingName_whenGetComputerByName_ThenReturnComputer() {
        String availableId = UUID.randomUUID().toString();
        String availableName = "Macbook Pro Retina";

        when(computerRepository.findComputerById(UUID.fromString(availableId))).thenReturn(
                Optional.of(new Computer(
                        UUID.fromString(availableId),
                        availableName,
                        "Macbook Pro Retina MF839LL/A 13.3-Inch Laptop with Retina Display (2.7 GHz Intel Core i5 Processor, 8 GB RAM, 128 GB Hard Drive, OS X Yosemite)",
                        new BigDecimal("1299.99"),
                        4.5f,
                        "https://stevenguyendev.com/images/macbook-pro-retina.jpg",
                        new Category(
                                "Macbook"
                        ),
                        new Brand(
                                "Apple"
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ))
        );

        when(computerRepository.findComputerByName(availableName)).thenReturn(
                Optional.of(new Computer(
                        UUID.fromString(availableId),
                        availableName,
                        "Macbook Pro Retina MF839LL/A 13.3-Inch Laptop with Retina Display (2.7 GHz Intel Core i5 Processor, 8 GB RAM, 128 GB Hard Drive, OS X Yosemite)",
                        new BigDecimal("1299.99"),
                        4.5f,
                        "https://stevenguyendev.com/images/macbook-pro-retina.jpg",
                        new Category(
                                "Macbook"
                        ),
                        new Brand(
                                "Apple"
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ))
        );

        ComputerDTO computer = underTest.findComputerByIdOrName(availableName);
        assertThat(computer.name()).isEqualTo(availableName);
        ComputerDTO computer2 = underTest.findComputerByIdOrName(availableId);
        assertThat(computer2.id().toString()).isEqualTo(availableId);
    }

    @Test
    void givenNoComputerMatchingName_whenGetComputerByName_ThenThrowException() {
        String invalidId = "123123";
        String unavailableName = "Bruh";
//        when(computerRepository.findComputerById(any(UUID.class))).thenReturn(Optional.empty());
//        when(computerRepository.findComputerByName(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findComputerByIdOrName(invalidId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Computer not found.");

        assertThatThrownBy(() -> underTest.findComputerByIdOrName(unavailableName))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Computer not found.");
    }
}
