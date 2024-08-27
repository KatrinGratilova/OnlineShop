package org.katrin.onlineshop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.katrin.onlineshop.model.ImageEntity;
import org.katrin.onlineshop.model.ProductEntity;
import org.katrin.onlineshop.model.UserEntity;
import org.katrin.onlineshop.repository.ImageRepository;
import org.katrin.onlineshop.repository.ProductRepository;
import org.katrin.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Slf4j

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ProductEntity> getByName(Optional<String> name) {
        return name.isPresent() ? productRepository.findByName(name.get()) : productRepository.findAll();
    }

    public ProductEntity getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void save(Principal principal, ProductEntity productEntity, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        productEntity.setUser(getUserByPrincipal(principal));

        addImageToProduct(productEntity, file1, true);
        addImageToProduct(productEntity, file2, false);
        addImageToProduct(productEntity, file3, false);

        log.info("Saving product: {}", productEntity.getName());

        ProductEntity savedProduct = productRepository.save(productEntity);
        savedProduct.setPreviewImageId(savedProduct.getImages().get(0).getId());

        productRepository.save(productEntity);
    }

    public UserEntity getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new UserEntity();
        return userRepository.findByEmail(principal.getName());
    }

    private void addImageToProduct(ProductEntity productEntity, MultipartFile file, boolean isPreviewImage) throws IOException {
        if (file != null && !file.isEmpty()) {
            ImageEntity imageEntity = toImageEntity(file);
            imageEntity.setPreviewImage(isPreviewImage);
            imageEntity.setProduct(productEntity);
            productEntity.getImages().add(imageEntity);
        }
    }

    private ImageEntity toImageEntity(MultipartFile file) throws IOException {
        return ImageEntity.builder().name(file.getName()).originalFileName(file.getOriginalFilename()).contentType(file.getContentType()).size(file.getSize()).bytes(file.getBytes()).build();
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
