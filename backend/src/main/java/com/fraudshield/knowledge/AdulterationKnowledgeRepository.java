package com.fraudshield.knowledge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdulterationKnowledgeRepository extends JpaRepository<AdulterationKnowledge, Long> {

    @Query("SELECT k FROM AdulterationKnowledge k WHERE " +
           "(:query IS NULL OR LOWER(k.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(k.commonAdulterants) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(k.description) LIKE LOWER(CONCAT('%', :query, '%'))) " +
           "AND (:category IS NULL OR LOWER(k.foodCategory) = LOWER(:category))")
    List<AdulterationKnowledge> searchKnowledge(@Param("query") String query, @Param("category") String category);

    List<AdulterationKnowledge> findByFoodCategoryIgnoreCase(String foodCategory);
}
