package org.katrin.onlineshop.repository;

import org.katrin.onlineshop.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
