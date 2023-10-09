package com.codefest.fundraiser.repository;

import com.codefest.fundraiser.model.FundRaiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRaiserRepository extends JpaRepository<FundRaiser, Long> {
}
