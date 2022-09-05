package com.blz.hiringcandidate.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blz.hiringcandidate.DTO.HiringCandidateDTO;
import com.blz.hiringcandidate.exception.CustomNotFoundexception;
import com.blz.hiringcandidate.model.HiringCandidateModel;
import com.blz.hiringcandidate.repository.HiringCandidateRepository;
import com.blz.hiringcandidate.util.TokenUtil;

@Service
public class HiringCandidateService implements IHiringCandidateService{

	@Autowired
	HiringCandidateRepository hiringCandidateRepository;
//
//	@Autowired
//	TokenUtil tokenUtil;

//	@Autowired
//	AdminRepository adminRepository;

	@Autowired
	MailService mailService;
	
	@Autowired
	RestTemplate restTemplate;

//	@Autowired
//	BankDetailsRepository bankDetailsRepository;

	@Override
	public HiringCandidateModel addHiringCandidate(HiringCandidateDTO hiringCandidateDTO,String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
//			Optional<BankDetailsModel> isDetailsPresent=bankDetailsRepository.findById(bankDetailsId);
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8012/hiringcandidate/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			HiringCandidateModel hiringCandidateModel = new HiringCandidateModel(hiringCandidateDTO);
//			if(isDetailsPresent.isPresent()) {
//				hiringCandidateModel.setBankdetails(isDetailsPresent.get());
//			}
			hiringCandidateRepository.save(hiringCandidateModel);
			String body = "Hiring candidate added successfully with Id"+hiringCandidateModel.getId();
			String subject = "Hiring candidate added Successfull";
			mailService.send(hiringCandidateModel.getEmail(), subject, body);
			return hiringCandidateModel;
		}
		throw new CustomNotFoundexception(400,"Token is invalid");
	}

	@Override
	public HiringCandidateModel updateHiringCandidate(HiringCandidateDTO hiringCandidateDTO, Long id,String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8012/hiringcandidate/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<HiringCandidateModel> ishiringcandidatepresent = hiringCandidateRepository.findById(id);
			if(ishiringcandidatepresent.isPresent()) {
				ishiringcandidatepresent.get().setCicId(hiringCandidateDTO.getCicId());
				ishiringcandidatepresent.get().setFullName(hiringCandidateDTO.getFullName());
				ishiringcandidatepresent.get().setEmail(hiringCandidateDTO.getEmail());
				ishiringcandidatepresent.get().setMobileNum(hiringCandidateDTO.getMobileNum());
				ishiringcandidatepresent.get().setHiredDate(hiringCandidateDTO.getHiredDate());
				ishiringcandidatepresent.get().setAggrPer(hiringCandidateDTO.getAggrPer());
				ishiringcandidatepresent.get().setCity(hiringCandidateDTO.getCity());
				ishiringcandidatepresent.get().setState(hiringCandidateDTO.getState());
				ishiringcandidatepresent.get().setJobLocation(hiringCandidateDTO.getJobLocation());
				ishiringcandidatepresent.get().setPassedOutYear(hiringCandidateDTO.getPassedOutYear());
				ishiringcandidatepresent.get().setCreatorUser(hiringCandidateDTO.getCreatorUser());
				ishiringcandidatepresent.get().setCandidateStatus(hiringCandidateDTO.getCandidateStatus());
				ishiringcandidatepresent.get().setUpdatedTimeStamp(hiringCandidateDTO.getUpdatedTimeStamp());
				hiringCandidateRepository.save(ishiringcandidatepresent.get());
				String body = "Hiring candidate updated successfully with Id"+ishiringcandidatepresent.get().getId();
				String subject = "Hiring candidate updated Successfully";
				mailService.send(ishiringcandidatepresent.get().getEmail(), subject, body);
				return ishiringcandidatepresent.get();
			}
			throw new CustomNotFoundexception(400,"Hiring candidate details not present");
		}
		throw new CustomNotFoundexception(400,"Token Invalid");
	}

	@Override
	public List<HiringCandidateModel> getAllHiringCandidate(String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8012/hiringcandidate/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<HiringCandidateModel> getAllHiringCandidate = hiringCandidateRepository.findAll();
			if(getAllHiringCandidate.size()>0) {
				return getAllHiringCandidate;
			}else {
				throw new CustomNotFoundexception(400,"Hiring candidate details not present");
			}
		}
		throw new CustomNotFoundexception(400,"Token invalid");
	}

	@Override
	public HiringCandidateModel deleteHiringCandidate(Long id,String token) {
//		Long admId = tokenUtil.decodeToken(token);
//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8012/hiringcandidate/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<HiringCandidateModel> isDetailsPresent = hiringCandidateRepository.findById(id);
			if(isDetailsPresent.isPresent()) {
				hiringCandidateRepository.delete(isDetailsPresent.get());
				String body = " Hiring candidate deleted successfully with Id"+isDetailsPresent.get().getId();
				String subject = "Hiring candidate deleted Successfully";
				mailService.send(isDetailsPresent.get().getEmail(), subject, body);
				return isDetailsPresent.get();
			}
			throw new CustomNotFoundexception(400,"Hiring candidate details not present");
		}
		throw new CustomNotFoundexception(400,"Token invalid");
	}
}
