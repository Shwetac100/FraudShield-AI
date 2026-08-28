package com.fraudshield.scan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackagedScanDetailsRepository extends JpaRepository<PackagedScanDetails, Long> {

    Optional<PackagedScanDetails> findByScanId(Long scanId);
}
