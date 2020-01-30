package com.ncarignan.pumas.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PumaRepository extends JpaRepository<Puma, Long> {
}
