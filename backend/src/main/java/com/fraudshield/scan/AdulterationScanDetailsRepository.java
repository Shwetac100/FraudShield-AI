package com.fraudshield.scan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdulterationScanDetailsRepository extends JpaRepository<AdulterationScanDetails, Long> {

    Optional<AdulterationScanDetails> findByScanId(Long scanId);
}
