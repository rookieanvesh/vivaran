package com.secure.vivaran.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String name;
   
   private String organization;
   
   @Column(nullable = false)
   private int rating;
   
   @Column(nullable = false, length = 1000)
   private String comment;
   
   private String useCase;
   
   @CreationTimestamp
   private LocalDateTime createdAt;
}