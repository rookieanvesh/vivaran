package com.secure.vivaran.services.impl;

import com.secure.vivaran.models.Review;
import com.secure.vivaran.repositories.ReviewRepository;
import com.secure.vivaran.services.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review updateReview(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null) {
            review.setName(reviewDetails.getName());
            review.setOrganization(reviewDetails.getOrganization());
            review.setRating(reviewDetails.getRating());
            review.setComment(reviewDetails.getComment());
            review.setUseCase(reviewDetails.getUseCase());
            return reviewRepository.save(review);
        }
        return null;
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}