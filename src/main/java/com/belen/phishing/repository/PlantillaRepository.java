package com.belen.phishing.repository;

import com.belen.phishing.model.PlantillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantillaRepository extends JpaRepository<PlantillaEntity, Integer> {

    @Query("SELECT p.html FROM PlantillaEntity p WHERE p.codPlantilla = :codPlantilla")
    String findHtmlByCodPlantilla(@Param("codPlantilla") String codPlantilla);

    @Query("SELECT p FROM PlantillaEntity p WHERE p.codPlantilla = :codPlantilla")
    PlantillaEntity findPlantillaByCodPlantilla(@Param("codPlantilla") String codPlantilla);
}
