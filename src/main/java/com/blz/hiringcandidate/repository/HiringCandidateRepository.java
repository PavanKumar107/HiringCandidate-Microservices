package com.blz.hiringcandidate.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blz.hiringcandidate.model.HiringCandidateModel;


public interface HiringCandidateRepository extends JpaRepository<HiringCandidateModel, Long>{

}
