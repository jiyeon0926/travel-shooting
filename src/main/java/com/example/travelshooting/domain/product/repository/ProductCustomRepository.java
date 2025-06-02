package com.example.travelshooting.domain.product.repository;

import com.example.travelshooting.domain.product.dto.ProductResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCustomRepository {

    Page<ProductResDto> findAllProducts(Pageable pageable, String productName);
}
