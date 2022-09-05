package com.blz.hiringcandidate.service;
import java.util.List;

import com.blz.hiringcandidate.DTO.HiringCandidateDTO;
import com.blz.hiringcandidate.model.HiringCandidateModel;



public interface IHiringCandidateService {
	
	HiringCandidateModel addHiringCandidate(HiringCandidateDTO hiringCandidateDTO,String token);
	
	HiringCandidateModel updateHiringCandidate(HiringCandidateDTO hiringCandidateDTO,Long id,String token);
	
	List<HiringCandidateModel> getAllHiringCandidate(String token);

	HiringCandidateModel deleteHiringCandidate(Long id,String token);

}
