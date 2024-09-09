
package org.stevenguyendev.pcshopwebsite;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.stevenguyendev.pcshopwebsite.model.*;
import org.stevenguyendev.pcshopwebsite.repository.*;

import java.math.BigDecimal;

@SpringBootApplication
public class PCShopApp {
    public static void main(String[] args) {
        SpringApplication.run(PCShopApp.class, args);
    }

//     @Bean
// 	public WebMvcConfigurer corsConfigurer() {
// 		return new WebMvcConfigurer() {
// 			@Override
// 			public void addCorsMappings(CorsRegistry registry) {
// 				registry.addMapping("/api/upload").allowedOrigins("http://localhost:4200");
// 			}
// 		};
// 	}

    @Bean
    @Profile({"dev", "local", "default"})
    CommandLineRunner runner(
            ComputerRepository computerRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository,
            CartRepository cartRepository,
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository
    ) {
        return args -> {
            cleanUpComputers(computerRepository, brandRepository, categoryRepository, orderItemRepository);
            addSampleComputers(computerRepository, brandRepository, categoryRepository);
            cleanUpUsersAndCarts(cartRepository, userRepository, orderRepository);
            addSampleUsersAndCarts(cartRepository, userRepository);
        };
    }

    private void addSampleUsersAndCarts(CartRepository cartRepository, UserRepository userRepository) {

        User user1 = User.builder().name("John Doe").email("johndoe@gmail.com").password("password").build();
//        user1 = userRepository.save(user1);
        Cart cart = Cart.builder().user(user1).build();
        cartRepository.save(cart);
    }

    private void cleanUpUsersAndCarts(
            CartRepository cartRepository,
            UserRepository userRepository,
            OrderRepository orderRepository) {
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }



    private void cleanUpComputers(
            ComputerRepository computerRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository,
            OrderItemRepository orderItemRepository) {
        orderItemRepository.deleteAll();
        computerRepository.deleteAll();
        brandRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    private void addSampleComputers(
            ComputerRepository computerRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository) {

        // Save brands
        Brand apple = brandRepository.save(new Brand("Apple"));
        Brand dell = brandRepository.save(new Brand("Dell"));
        Brand lenovo = brandRepository.save(new Brand("Lenovo"));
        Brand hp = brandRepository.save(new Brand("HP"));

        // Save categories
        Category laptop = categoryRepository.save(new Category("Laptop"));
        Category desktop = categoryRepository.save(new Category("Desktop"));
        Category macbook = categoryRepository.save(new Category("Macbook"));

        // Insert sample computers
        computerRepository.save(Computer.builder()
                .name("Macbook Pro 13\", Intel i5, 8GB RAM, 256GB SSD")
                .description("13-inch Macbook Pro with Retina Display")
                .price(new BigDecimal("1299.99"))
                .rating(4.5f)
                .thumbnail("macbook-pro-13.jpg")
                .category(macbook)
                .brand(apple)
                .specification("Specs for Macbook Pro 13-inch")
                .build()
        );

        computerRepository.save(Computer.builder()
                .name("Macbook Pro 16\", Intel i7, 16GB RAM, 512GB SSD")
                .description("16-inch Macbook Pro with Retina Display")
                .price(new BigDecimal("2399.99"))
                .rating(4.8f)
                .thumbnail("macbook-pro-16.jpg")
                .category(macbook)
                .brand(apple)
                .specification("Specs for Macbook Pro 16-inch")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkPad X1 Carbon, Intel i5, 16GB RAM, 512GB SSD")
                .description("14-inch Lenovo ThinkPad X1 Carbon")
                .price(new BigDecimal("1399.99"))
                .rating(4.6f)
                .thumbnail("thinkpad-x1-carbon.jpg")
                .category(laptop)
                .brand(lenovo)
                .specification("Specs for ThinkPad X1 Carbon")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkPad X1 Extreme, Intel i7, 32GB RAM, 1TB SSD")
                .description("15-inch Lenovo ThinkPad X1 Extreme")
                .price(new BigDecimal("1999.99"))
                .rating(4.7f)
                .thumbnail("thinkpad-x1-extreme.jpg")
                .category(laptop)
                .brand(lenovo)
                .specification("Specs for ThinkPad X1 Extreme")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkPad X1 Yoga, Intel i7, 16GB RAM, 512GB SSD")
                .description("14-inch Lenovo ThinkPad X1 Yoga")
                .price(new BigDecimal("1799.99"))
                .rating(4.7f)
                .thumbnail("thinkpad-x1-yoga.jpg")
                .category(laptop)
                .brand(lenovo)
                .specification("Specs for ThinkPad X1 Yoga")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkPad P1, Intel i7, 32GB RAM, 1TB SSD")
                .description("15-inch Lenovo ThinkPad P1")
                .price(new BigDecimal("1999.99"))
                .rating(4.7f)
                .thumbnail("thinkpad-p1.jpg")
                .category(laptop)
                .brand(lenovo)
                .specification("Specs for ThinkPad P1")
                .build());

        computerRepository.save(Computer.builder()
                .name("Dell Latitude 15, Intel i5, 8GB RAM, 512GB SSD")
                .description("15-inch Dell Latitude laptop")
                .price(new BigDecimal("1099.99"))
                .rating(4.3f)
                .thumbnail("dell-latitude-15.jpg")
                .category(laptop)
                .brand(dell)
                .specification("Specs for Dell Latitude 15")
                .build());

        computerRepository.save(Computer.builder()
                .name("Dell XPS 13, Intel i7, 16GB RAM, 512GB SSD")
                .description("13-inch Dell XPS laptop")
                .price(new BigDecimal("1399.99"))
                .rating(4.5f)
                .thumbnail("dell-xps-13.jpg")
                .category(laptop)
                .brand(dell)
                .specification("Specs for Dell XPS 13")
                .build());

        computerRepository.save(Computer.builder()
                .name("Dell XPS 15, Intel i7, 32GB RAM, 1TB SSD")
                .description("15-inch Dell XPS laptop")
                .price(new BigDecimal("1999.99"))
                .rating(4.7f)
                .thumbnail("dell-xps-15.jpg")
                .category(laptop)
                .brand(dell)
                .specification("Specs for Dell XPS 15")
                .build());

        computerRepository.save(Computer.builder()
                .name("Dell Precision 15, Intel i7, 32GB RAM, 1TB SSD")
                .description("15-inch Dell Precision laptop")
                .price(new BigDecimal("1999.99"))
                .rating(4.7f)
                .thumbnail("dell-precision-15.jpg")
                .category(laptop)
                .brand(dell)
                .specification("Specs for Dell Precision 15")
                .build());

        computerRepository.save(Computer.builder()
                .name("Dell Inspiron 15, Intel i5, 8GB RAM, 512GB SSD")
                .description("15-inch Dell Inspiron laptop")
                .price(new BigDecimal("1099.99"))
                .rating(4.3f)
                .thumbnail("dell-inspiron-15.jpg")
                .category(laptop)
                .brand(dell)
                .specification("Specs for Dell Inspiron 15")
                .build());

        computerRepository.save(Computer.builder()
                .name("HP EliteBook 840, Intel i5, 8GB RAM, 512GB SSD")
                .description("14-inch HP EliteBook laptop")
                .price(new BigDecimal("1099.99"))
                .rating(4.3f)
                .thumbnail("hp-elitebook-840.jpg")
                .category(laptop)
                .brand(hp)
                .specification("Specs for HP EliteBook 840")
                .build());

        computerRepository.save(Computer.builder()
                .name("HP EliteBook 850, Intel i7, 16GB RAM, 512GB SSD")
                .description("15-inch HP EliteBook laptop")
                .price(new BigDecimal("1399.99"))
                .rating(4.5f)
                .thumbnail("hp-elitebook-850.jpg")
                .category(laptop)
                .brand(hp)
                .specification("Specs for HP EliteBook 850")
                .build());

        computerRepository.save(Computer.builder()
                .name("HP ZBook 15, Intel i7, 32GB RAM, 1TB SSD")
                .description("15-inch HP ZBook laptop")
                .price(new BigDecimal("1999.99"))
                .rating(4.7f)
                .thumbnail("hp-zbook-15.jpg")
                .category(laptop)
                .brand(hp)
                .specification("Specs for HP ZBook 15")
                .build());

        computerRepository.save(Computer.builder()
                .name("HP ProBook 450, Intel i5, 8GB RAM, 512GB SSD")
                .description("15-inch HP ProBook laptop")
                .price(new BigDecimal("1099.99"))
                .rating(4.3f)
                .thumbnail("hp-probook-450.jpg")
                .category(laptop)
                .brand(hp)
                .specification("Specs for HP ProBook 450")
                .build());

        computerRepository.save(Computer.builder()
                .name("Apple MacBook Pro 13\", M1, 8GB RAM, 256GB SSD")
                .description("13-inch Apple MacBook Pro")
                .price(new BigDecimal("1299.99"))
                .rating(4.5f)
                .thumbnail("macbook-pro-13.jpg")
                .category(laptop)
                .brand(apple)
                .specification("Specs for Apple MacBook Pro 13\"")
                .build());

        computerRepository.save(Computer.builder()
                .name("Apple MacBook Pro 16\", Intel i7, 16GB RAM, 512GB SSD")
                .description("16-inch Apple MacBook Pro")
                .price(new BigDecimal("1999.99"))
                .rating(4.7f)
                .thumbnail("macbook-pro-16.jpg")
                .category(laptop)
                .brand(apple)
                .specification("Specs for Apple MacBook Pro 16\"")
                .build());

        computerRepository.save(Computer.builder()
                .name("Apple MacBook Air 11\", Intel Core 2 Duo, 4GB RAM, 128GB SSD")
                .description("11-inch Apple MacBook Air")
                .price(new BigDecimal("799.99"))
                .rating(4.1f)
                .thumbnail("macbook-air-11.jpg")
                .category(laptop)
                .brand(apple)
                .specification("Specs for Apple MacBook Air 11\"")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkPad X1 Carbon, Intel i7, 16GB RAM, 1TB SSD")
                .description("14-inch Lenovo ThinkPad")
                .price(new BigDecimal("1899.99"))
                .rating(4.9f)
                .thumbnail("thinkpad-x1-carbon.jpg")
                .category(laptop)
                .brand(lenovo)
                .specification("Specs for Lenovo ThinkPad X1 Carbon")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo IdeaPad 14, Intel i3, 8GB RAM, 512GB SSD")
                .description("14-inch Lenovo IdeaPad laptop")
                .price(new BigDecimal("799.99"))
                .rating(4.3f)
                .thumbnail("ideapad-14.jpg")
                .category(laptop)
                .brand(lenovo)
                .specification("Specs for Lenovo IdeaPad 14")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkCentre M90n, Intel i5, 8GB RAM, 512GB SSD")
                .description("Lenovo ThinkCentre desktop")
                .price(new BigDecimal("999.99"))
                .rating(4.6f)
                .thumbnail("thinkcentre-m90n.jpg")
                .category(desktop)
                .brand(lenovo)
                .specification("Specs for ThinkCentre M90n")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo Legion Tower 5, Intel i7, 16GB RAM, 1TB SSD")
                .description("Lenovo Legion gaming desktop")
                .price(new BigDecimal("1599.99"))
                .rating(4.8f)
                .thumbnail("legion-tower-5.jpg")
                .category(desktop)
                .brand(lenovo)
                .specification("Specs for Legion Tower 5")
                .build());

        computerRepository.save(Computer.builder()
                .name("Lenovo ThinkStation P340, Intel i7, 32GB RAM, 1TB SSD")
                .description("Lenovo ThinkStation desktop")
                .price(new BigDecimal("1999.99"))
                .rating(4.9f)
                .thumbnail("thinkstation-p340.jpg")
                .category(desktop)
                .brand(lenovo)
                .specification("Specs for ThinkStation P340")
                .build());
    }
}