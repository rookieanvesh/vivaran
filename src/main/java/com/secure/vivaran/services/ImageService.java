package com.secure.vivaran.services;

import com.secure.vivaran.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    /**
     * Saves an image file for a specific user
     * @param file The image file to save
     * @param username The username of the image owner
     * @return The saved image entity
     * @throws IOException If there's an error processing the file
     */
    Image saveImage(MultipartFile file, String username) throws IOException;
    
    /**
     * Retrieves an image for a specific user
     * @param id The ID of the image to retrieve
     * @param username The username of the image owner
     * @return The requested image entity
     * @throws IllegalStateException if the image is not found
     */
    Image getImage(Long id, String username);
    
    /**
     * Deletes an image for a specific user
     * @param id The ID of the image to delete
     * @param username The username of the image owner
     * @throws IllegalStateException if the image is not found
     */
    void deleteImage(Long id, String username);
}