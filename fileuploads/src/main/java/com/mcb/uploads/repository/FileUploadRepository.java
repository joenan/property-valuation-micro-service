package com.mcb.uploads.repository;


import com.mcb.commons.entities.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

    @Query(value = "SELECT * FROM file_uploads WHERE user_id = :userId AND property_valuation_id= :propertyId", nativeQuery = true)
    List<FileUpload> findFileUploadsByUploadedByAndPropertyId(@Param("userId") Long userId,
                                                              @Param("propertyId") Long propertyId);
}