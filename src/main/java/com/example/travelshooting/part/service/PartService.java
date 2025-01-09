package com.example.travelshooting.part.service;

import com.example.travelshooting.part.Part;
import com.example.travelshooting.part.dto.CreatePartReqDto;
import com.example.travelshooting.part.dto.PartResDto;
import com.example.travelshooting.part.dto.UpdatePartReqDto;
import com.example.travelshooting.part.repository.PartRepository;
import com.example.travelshooting.product.Product;
import com.example.travelshooting.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {

    private final ProductService productService;
    private final PartRepository partRepository;

    public PartResDto createPart(Long productId, CreatePartReqDto createPartReqDto) {
        Product product = productService.findProductById(productId);
        Part part = createPartReqDto.toEntity(product);
        Part savedPart = partRepository.save(part);

        return  new PartResDto(
                savedPart.getId(),
                savedPart.getOpenAt(),
                savedPart.getCloseAt(),
                savedPart.getNumber()
        );
    }

    @Transactional
    public PartResDto updatePart(Long partId, UpdatePartReqDto updatePartReqDto) {
        Part findPart = partRepository.findByIdOrElseThrow(partId);
        findPart.updatePart(
                updatePartReqDto.getOpenAt(),
                updatePartReqDto.getCloseAt(),
                updatePartReqDto.getNumber()
        );
        partRepository.save(findPart);

        return new PartResDto(
                findPart.getId(),
                findPart.getOpenAt(),
                findPart.getCloseAt(),
                findPart.getNumber()
        );
    }

    @Transactional
    public void deleteCompany(Long partId) {
        Part findPart = partRepository.findByIdOrElseThrow(partId);
        partRepository.delete(findPart);
    }

    public Part findPartById(Long partId) {
        return partRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("아이디 " + partId + "에 해당하는 레저/티켓 일정을 찾을 수 없습니다."));
    }

    public Part findPartByProductId(Long productId) {
        Part part = partRepository.findPartByProductId(productId);

        if (part == null) {
            throw new IllegalArgumentException("아이디 " + part.getId() + "에 해당하는 레저/티켓 일정을 찾을 수 없습니다.");
        }

        return part;
    }

    public List<Part> findPartsByProductId(Long productId) {

        List<Part> parts = partRepository.findPartsByProductId(productId);

        if (parts.isEmpty()) {
            throw new IllegalArgumentException("아이디 " + productId + "에 해당하는 레저/티켓 일정을 찾을 수 없습니다.");
        }

        return parts;
    }

}
