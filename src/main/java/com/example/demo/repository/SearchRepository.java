package com.example.demo.repository;

import com.example.demo.domain.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, Long> {
    boolean existsByKeyWord(String keyWord);

    @Query("SELECT r.keyWord FROM SearchEntity r where r.id = :id")
    Long findIdBykeyWord(@Param("keyWord") String keyWord);

    // @Query("SELECT r.count FROM (SELECT r.count FROM SearchEntity r ORDER BY r.count DESC) WHERE ROWNUM <= 10")

    List<SearchEntity> findTop10ByCount();
}
