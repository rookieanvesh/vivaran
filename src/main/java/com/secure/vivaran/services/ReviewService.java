package com.secure.vivaran.services;

import com.secure.vivaran.models.Review;

import java.util.List;

public interface ReviewService {
   Review saveReview(Review review);
   List<Review> getAllReviews();
   Review getReviewById(Long id);
   Review updateReview(Long id, Review review);
   void deleteReview(Long id);
}