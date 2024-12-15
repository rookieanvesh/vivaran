package com.secure.vivaran.repositories;

import com.secure.vivaran.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByUsername(String username);
    Optional<Image> findByIdAndUsername(Long id, String username);
}