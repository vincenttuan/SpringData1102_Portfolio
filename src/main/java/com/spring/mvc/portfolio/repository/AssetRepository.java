package com.spring.mvc.portfolio.repository;

import com.spring.mvc.portfolio.entities.Asset;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "assetRepository")
public interface AssetRepository extends JpaRepository<Asset, Integer>{
    @Query(value = "SELECT a FROM Asset a WHERE a.id = ?1")
    public List<Asset> findById(@Param("id") Integer id);
}