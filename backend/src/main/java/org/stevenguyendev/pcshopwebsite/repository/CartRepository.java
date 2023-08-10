package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
     Cart findByUserId(Long userId);

    Cart findByUserEmail(String userEmail);
}
