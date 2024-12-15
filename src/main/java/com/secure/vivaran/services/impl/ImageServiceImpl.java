package com.secure.vivaran.services.impl;

import com.secure.vivaran.models.Image;
import com.secure.vivaran.repositories.ImageRepository;
import com.secure.vivaran.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    
    private final ImageRepository imageRepository;
    
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    @Override
    public Image saveImage(MultipartFile file, String username) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        
        // Validate file type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }
        
        Image image = new Image();
        image.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
        image.setContentType(contentType);
        image.setData(file.getBytes());
        image.setUsername(username);
        
        log.info("Saving image {} for user {}", image.getFilename(), username);
        return imageRepository.save(image);
    }
    
    @Override
    public Image getImage(Long id, String username) {
        log.info("Retrieving image {} for user {}", id, username);
        return imageRepository.findByIdAndUsername(id, username)
            .orElseThrow(() -> new IllegalStateException(
                String.format("Image %d not found for user %s", id, username)));
    }
    
    @Override
    public void deleteImage(Long id, String username) {
        log.info("Deleting image {} for user {}", id, username);
        Image image = imageRepository.findByIdAndUsername(id, username)
            .orElseThrow(() -> new IllegalStateException(
                String.format("Image %d not found for user %s", id, username)));
                
        imageRepository.delete(image);
        log.info("Image {} deleted successfully", id);
    }
}
