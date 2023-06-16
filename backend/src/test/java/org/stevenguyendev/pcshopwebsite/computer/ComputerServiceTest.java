package org.stevenguyendev.pcshopwebsite.computer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stevenguyendev.pcshopwebsite.computer.dao.ComputerDAO;
import org.stevenguyendev.pcshopwebsite.computer.dto.*;
import org.stevenguyendev.pcshopwebsite.computer.entity.Brand;
import org.stevenguyendev.pcshopwebsite.computer.entity.Category;
import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;
import org.stevenguyendev.pcshopwebsite.computer.entity.Media;
import org.stevenguyendev.pcshopwebsite.computer.request.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.computer.service.ComputerService;
import org.stevenguyendev.pcshopwebsite.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {
    @Mock
    private ComputerDAO computerDAO;
    private BrandDTOMapper brandDTOMapper = new BrandDTOMapper();
    private CategoryDTOMapper categoryDTOMapper = new CategoryDTOMapper();
    private ComputerDTOMapper computerDTOMapper = new ComputerDTOMapper(
            brandDTOMapper,
            categoryDTOMapper
    );
    private CompleteComputerDTOMapper completeComputerDTOMapper = new CompleteComputerDTOMapper(
            brandDTOMapper,
            categoryDTOMapper,
            new MediaDTOMapper()
    );
    private ComputerService underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new ComputerService(
                computerDAO,
                computerDTOMapper,
                completeComputerDTOMapper,
                brandDTOMapper,
                categoryDTOMapper
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
        when(computerDAO.getFilteredAndSortedComputers(
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
                        null,
                        List.of(
                                new Media(),
                                new Media(),
                                new Media(),
                                new Media()
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new Computer(
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
                        null,
                        List.of(
                                new Media(),
                                new Media(),
                                new Media(),
                                new Media()
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        ));

        List<ComputerDTO> computers = underTest.getFilteredAndSortedComputers(request);
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
        when(computerDAO.getFilteredAndSortedComputers(
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

        List<ComputerDTO> computers1 = underTest.getFilteredAndSortedComputers(request1);
        assertThat(computers1).hasSize(0);
    }

    @Test
    void givenComputerWithMatchingName_whenGetComputerByName_ThenReturnComputer() {
        String name = "Macbook Pro Retina";
        when(computerDAO.getComputerByName(name)).thenReturn(
                Optional.of(new Computer(
                        name,
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
                        null,
                        List.of(
                                new Media(),
                                new Media(),
                                new Media(),
                                new Media()
                        ),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ))
        );

        CompleteComputerDTO computer = underTest.getComputerByName(name);
        assertThat(computer.name()).isEqualTo(name);
    }

    @Test
    void givenNoComputerMatchingName_whenGetComputerByName_ThenThrowException() {
        String name = "Macbook Pro Retina";
        when(computerDAO.getComputerByName(name)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getComputerByName(name))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Computer with name " + name + " not found");
    }

    @Test
    void returnsAllBrands_whenGetAllBrands() {
        when(computerDAO.getAllBrands()).thenReturn(List.of(
                new Brand(
                        "Apple"
                ),
                new Brand(
                        "Dell"
                )
        ));

        List<BrandDTO> brands = underTest.getAllBrands();
        assertThat(brands).hasSize(2);
        assertThat(brands.get(0).name()).isEqualTo("Apple");
        assertThat(brands.get(1).name()).isEqualTo("Dell");
    }

    @Test
    void returnsAllCategory_whenGetAllCategories() {
        when(computerDAO.getAllCategories()).thenReturn(List.of(
                new Category(
                        "Macbook"
                ),
                new Category(
                        "Laptop"
                )
        ));

        List<CategoryDTO> categories = underTest.getAllCategories();
        assertThat(categories).hasSize(2);
        assertThat(categories.get(0).name()).isEqualTo("Macbook");
        assertThat(categories.get(1).name()).isEqualTo("Laptop");
    }
}
