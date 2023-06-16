package org.stevenguyendev.pcshopwebsite.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.stevenguyendev.pcshopwebsite.AbstractTestcontainers;
import org.stevenguyendev.pcshopwebsite.computer.dto.ComputerDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComputerControllerIT extends AbstractTestcontainers {

    @Autowired
    private WebTestClient webTestClient;

    private static final String BASE_URL = "/api/v1/computers";
    private static final Random RANDOM = new Random();
    private static final String PC_CATEGORY = "PC";
    private static final String MACBOOK_CATEGORY = "Macbook";
    private static final String LENOVO_BRAND = "Lenovo";
    private static final String DELL_BRAND = "Dell";

    @Test
    void givenComputersWithAccessGranted_WhenGetAllComputers_ThenReturnAllComputers() {
        List<ComputerDTO> computers = webTestClient
                .get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ComputerDTO.class)
                .returnResult()
                .getResponseBody();
        assertThat(computers).isNotEmpty();
        assertThat(computers.size()).isLessThanOrEqualTo(50);

        ComputerDTO computer = computers.get(0);
        assertThat(computer.description()).isNotNull();
        assertThat(computer.price()).isNotNull();
        assertThat(computer.rating()).isNotNull();
        assertThat(computer.thumbnail()).isNotNull();
        assertThat(isValidImageURL(computer.thumbnail())).isEqualTo(true);
        assertThat(computer.brand()).isNotNull();
        assertThat(computer.brand().name()).isNotNull();
        assertThat(computer.category()).isNotNull();
        assertThat(computer.category().name()).isNotNull();
        assertThat(computer.updatedAt()).isNotNull();
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByName_ThenReturnMatchingComputers() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL)
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        String searchTxt = computers.get(0)
                                    .name()
                                    .substring(0, computers.get(0)
                                                           .name()
                                                           .length() - 1);

        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?name=" + searchTxt)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();

        ComputerDTO computer = computers2.get(0);
        assertThat(computer.name()).contains(searchTxt);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByCategory_ThenReturnMatchingComputers() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?categories=" + PC_CATEGORY)
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();

        ComputerDTO someComputer = computers.get(RANDOM.nextInt(0, computers.size()));
        assertThat(someComputer.category().name()).isEqualTo(PC_CATEGORY);

        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?categories=" + PC_CATEGORY + "," + MACBOOK_CATEGORY)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();

        ComputerDTO anotherComputer = computers2.get(RANDOM.nextInt(0, computers2.size()));
        assertThat(anotherComputer.category()
                                  .name()).isIn(PC_CATEGORY, MACBOOK_CATEGORY);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByBrand_ThenReturnMatchingComputers() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?brands=" + LENOVO_BRAND)
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();

        ComputerDTO someComputer = computers.get(RANDOM.nextInt(0, computers.size()));
        assertThat(someComputer.brand().name()).isEqualTo(LENOVO_BRAND);

        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?brands=" + LENOVO_BRAND + "," + DELL_BRAND)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();

        ComputerDTO anotherComputer = computers2.get(RANDOM.nextInt(0, computers2.size()));
        assertThat(anotherComputer.brand()
                                  .name()).isIn(LENOVO_BRAND, DELL_BRAND);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByPrice_ThenReturnMatchingComputers() {
        BigDecimal minPrice = new BigDecimal(RANDOM.nextFloat(500.0f, 1000.0f));
        BigDecimal maxPrice = new BigDecimal(RANDOM.nextFloat(1000.0f, 1500.0f));
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?minPrice=" + minPrice)
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();
        ComputerDTO computer = computers.get(RANDOM.nextInt(0, computers.size()));
        assertThat(computer.price()).isGreaterThanOrEqualTo(minPrice);

        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?maxPrice=" + maxPrice)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();
        ComputerDTO computer2 = computers2.get(RANDOM.nextInt(0, computers2.size()));
        assertThat(computer2.price()).isLessThanOrEqualTo(maxPrice);

        List<ComputerDTO> computers3 = webTestClient.get()
                                                    .uri(BASE_URL + "?minPrice=" + minPrice + "&maxPrice=" + maxPrice)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers3).isNotNull();
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByRating_ThenReturnMatchingComputers() {
        Float rating = 1.0f;
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?rating=" + rating)
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();
        ComputerDTO computer = computers.get(RANDOM.nextInt(0, computers.size()));
        assertThat(computer.rating()).isGreaterThanOrEqualTo(rating);
    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByNameDesc_ThenReturnComputersSortedByNameDesc() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?sort=name")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();
        List<String> computerNames = computers.stream()
                                              .map(ComputerDTO::name)
                                              .collect(Collectors.toList());
        assertThat(validateAlphabetOrderDesc(computerNames)).isEqualTo(true);
    }

    private boolean validateDateOrderAsc(List<LocalDateTime> computerDates2) {
        LocalDateTime previous = LocalDateTime.MIN;
        for (LocalDateTime current : computerDates2) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private boolean validateDateOrderDesc(List<LocalDateTime> computerDates) {
        LocalDateTime previous = LocalDateTime.MIN;
        for (int i = computerDates.size() - 1; i >= 0; i--) {
            LocalDateTime current = computerDates.get(i);
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private boolean validateRatingOrderAsc(List<Float> computerRatings2) {
        Float previous = 0.0f;
        for (Float current : computerRatings2) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private boolean validateRatingOrderDesc(List<Float> computerRatings) {
        Float previous = 0.0f;
        for (int i = computerRatings.size() - 1; i >= 0; i--) {
            Float current = computerRatings.get(i);
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private boolean validatePriceOrderAsc(List<BigDecimal> computerPrices2) {
        BigDecimal previous = BigDecimal.ZERO;
        for (BigDecimal current : computerPrices2) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private boolean validatePriceOrderDesc(List<BigDecimal> computerPrices) {
        BigDecimal previous = BigDecimal.ZERO;
        for (int i = computerPrices.size() - 1; i >= 0; i--) {
            BigDecimal current = computerPrices.get(i);
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private static boolean validateAlphabetOrderAsc(List<String> list) {
        String previous = ""; // empty string: guaranteed to be less than or equal to any other
        for (String current : list) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    private static boolean validateAlphabetOrderDesc(List<String> list) {
        String previous = ""; // empty string: guaranteed to be less than or equal to any other
        for (int i = list.size() - 1; i >= 0; i--) {
            String current = list.get(i);
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByNameAsc_ThenReturnComputersSortedByNameAsc() {

        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=name&order=asc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();
        List<String> computerNames2 = computers2.stream()
                                                .map(ComputerDTO::name)
                                                .collect(Collectors.toList());
        assertThat(validateAlphabetOrderAsc(computerNames2)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByPriceDesc_ThenReturnComputersSortedByPriceDesc() {

        List<ComputerDTO> computers3 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=price&order=desc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers3).isNotEmpty();
        List<BigDecimal> computerPrices = computers3.stream()
                                                    .map(ComputerDTO::price)
                                                    .collect(Collectors.toList());
        assertThat(validatePriceOrderDesc(computerPrices)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByPriceAsc_ThenReturnComputersSortedByPriceAsc() {

        List<ComputerDTO> computers4 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=price&order=asc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers4).isNotEmpty();
        List<BigDecimal> computerPrices2 = computers4.stream()
                                                     .map(ComputerDTO::price)
                                                     .collect(Collectors.toList());
        assertThat(validatePriceOrderAsc(computerPrices2)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByRatingDesc_ThenReturnComputersSortedByRatingDesc() {

        List<ComputerDTO> computers5 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=rating&order=desc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers5).isNotEmpty();
        List<Float> computerRatings = computers5.stream()
                                                .map(ComputerDTO::rating)
                                                .collect(Collectors.toList());
        assertThat(validateRatingOrderDesc(computerRatings)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByRatingAsc_ThenReturnComputersSortedByRatingAsc() {

        List<ComputerDTO> computers6 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=rating&order=asc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers6).isNotEmpty();
        List<Float> computerRatings2 = computers6.stream()
                                                 .map(ComputerDTO::rating)
                                                 .collect(Collectors.toList());
        assertThat(validateRatingOrderAsc(computerRatings2)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByUpdatedAtDesc_ThenReturnComputersSortedByUpdatedAtDesc() {

        List<ComputerDTO> computers7 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=updatedAt&order=desc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers7).isNotEmpty();
        List<LocalDateTime> computerDates = computers7.stream()
                                                      .map(ComputerDTO::updatedAt)
                                                      .collect(Collectors.toList());
        assertThat(validateDateOrderDesc(computerDates)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedByUpdatedAtAsc_ThenReturnComputersSortedByUpdatedAtAsc() {

        List<ComputerDTO> computers8 = webTestClient.get()
                                                    .uri(BASE_URL + "?sort=updatedAt&order=asc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers8).isNotEmpty();
        List<LocalDateTime> computerDates2 = computers8.stream()
                                                       .map(ComputerDTO::updatedAt)
                                                       .collect(Collectors.toList());
        assertThat(validateDateOrderAsc(computerDates2)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenGetComputersSortedBySomethingElse_ThenReturnComputersSortedByUpdatedAt() {

        List<ComputerDTO> computers7 = webTestClient.get()
                                                    .uri(BASE_URL)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers7).isNotEmpty();
        List<LocalDateTime> computerDates = computers7.stream()
                                                      .map(ComputerDTO::updatedAt)
                                                      .collect(Collectors.toList());
        assertThat(validateDateOrderDesc(computerDates)).isEqualTo(true);

    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerWithSortAndOrder_ThenReturnSortedOrderedComputers() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?sort=price&order=desc")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();
        List<BigDecimal> computerPrices = computers.stream()
                                                    .map(ComputerDTO::price)
                                                    .collect(Collectors.toList());
        assertThat(validatePriceOrderDesc(computerPrices)).isEqualTo(true);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerWithPageAndSize_ThenReturnComputersWithPageAndSize() {

        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?page=0&size=2")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        assertThat(computers).isNotEmpty();
        assertThat(computers.size()).isEqualTo(2);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByBrandAndCategory_ThenReturnMatchingComputers() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?page=0&size=2")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        String brand = computers.get(0).brand().name();
        String category = computers.get(0).category().name();
        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?brands=" + brand + "&categories=" + category)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();
        assertThat(computers2.get(0).brand().name()).isEqualTo(brand);
        assertThat(computers2.get(0).category().name()).isEqualTo(category);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByBrandAndCategoryAndPrice_ThenReturnMatchingComputers() {
        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?page=0&size=2")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        String brand = computers.get(0).brand().name();
        String category = computers.get(0).category().name();
        BigDecimal price = computers.get(0).price();
        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?brands=" + brand + "&categories=" + category + "&minPrice=" + price + "&maxPrice=" + price)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();
        assertThat(computers2.get(0).brand().name()).isEqualTo(brand);
        assertThat(computers2.get(0).category().name()).isEqualTo(category);
        assertThat(computers2.get(0).price()).isEqualTo(price);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByBrandAndCategoryAndPriceAndRating_ThenReturnMatchingComputers() {

        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?page=0&size=2")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        String brand = computers.get(0).brand().name();
        String category = computers.get(0).category().name();
        BigDecimal price = computers.get(0).price();
        Float rating = computers.get(0).rating();
        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?brands=" + brand + "&categories=" + category + "&minPrice=" + price + "&maxPrice=" + price + "&minRating=" + rating)
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();
        assertThat(computers2.get(0).brand().name()).isEqualTo(brand);
        assertThat(computers2.get(0).category().name()).isEqualTo(category);
        assertThat(computers2.get(0).price()).isEqualTo(price);
        assertThat(computers2.get(0).rating()).isEqualTo(rating);
    }

    @Test
    void givenComputersWithAccessGranted_WhenFilterComputerByBrandAndCategoryAndPriceAndRatingAndSortAndOrder_ThenReturnMatchingComputersSortedOrdered() {

        List<ComputerDTO> computers = webTestClient.get()
                                                   .uri(BASE_URL + "?page=0&size=2")
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(ComputerDTO.class)
                                                   .returnResult()
                                                   .getResponseBody();
        String brand = computers.get(0).brand().name();
        String category = computers.get(0).category().name();
        BigDecimal price = computers.get(0).price();
        Float rating = computers.get(0).rating();
        List<ComputerDTO> computers2 = webTestClient.get()
                                                    .uri(BASE_URL + "?brands=" + brand + "&categories=" + category + "&minPrice=" + price + "&minRating=" + rating + "&sort=price&order=asc")
                                                    .exchange()
                                                    .expectStatus()
                                                    .isOk()
                                                    .expectBodyList(ComputerDTO.class)
                                                    .returnResult()
                                                    .getResponseBody();
        assertThat(computers2).isNotEmpty();
        assertThat(computers2.get(0).brand().name()).isEqualTo(brand);
        assertThat(computers2.get(0).category().name()).isEqualTo(category);
        assertThat(computers2.get(0).price()).isEqualTo(price);
        assertThat(computers2.get(0).rating()).isEqualTo(rating);
        assertThat(validatePriceOrderAsc(computers2.stream()
                                                   .map(ComputerDTO::price)
                                                   .collect(Collectors.toList()))).isEqualTo(true);
    }

    private boolean isValidImageURL(String url) {
        return url.matches("^https?://.*\\.(png|jpg|jpeg|gif)$");
    }

}
