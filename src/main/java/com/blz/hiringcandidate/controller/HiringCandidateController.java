package com.blz.hiringcandidate.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blz.hiringcandidate.DTO.HiringCandidateDTO;
import com.blz.hiringcandidate.model.HiringCandidateModel;
import com.blz.hiringcandidate.service.IHiringCandidateService;
import com.blz.hiringcandidate.util.Response;




/**
 * Purpose: HiringCandidate controller to process HiringCandidate Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V  
 */
@RestController
@RequestMapping("/hiringcandidate")
public class HiringCandidateController {

	@Autowired
	IHiringCandidateService hiringCandidateService;

	/**
	 * Purpose: To create HiringCandidate
	 * @Param: hiringCandidateDTO, token and bankDetailsId
	 */
	@PostMapping("/addhiringcandidate")
	public ResponseEntity<Response> addHiringCandidate(@Valid@RequestBody HiringCandidateDTO hiringCandidateDTO, @RequestHeader String token) {
		HiringCandidateModel hiringCandidateModel = hiringCandidateService.addHiringCandidate(hiringCandidateDTO, token);
		Response response = new Response("HiringCandidate inserted successfully", 200, hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose: To update HiringCandidate details by id
	 * @Param: hiringCandidateDTO,id and token
	 */
	@PutMapping("/updatehiringcandidate/{id}")
	public ResponseEntity<Response> updateHiringCandidate(@RequestBody HiringCandidateDTO hiringCandidateDTO,@PathVariable Long id, @RequestHeader String token) {
		HiringCandidateModel hiringCandidateModel = hiringCandidateService.updateHiringCandidate(hiringCandidateDTO,id,token);
		Response response = new Response("HiringCandidate updated successfully", 200, hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: To get all HiringCandidate data
	 * @Param: token 
	 */
	@GetMapping("getallhiringcandidates")
	public ResponseEntity<Response> getAllHiringCandidate(@RequestHeader String token) {
		List<HiringCandidateModel> hiringCandidateModel = hiringCandidateService.getAllHiringCandidate(token);
		Response response = new Response("Getting all the HiringCandidates successfully", 200, hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: To delete HiringCandidate details by id
	 * @Param: id and token
	 */
	@DeleteMapping("/deletehiringcandidate/{id}")
	public ResponseEntity<Response> deleteHiringCandidate(@PathVariable Long id, @RequestHeader String token) {
		HiringCandidateModel hiringCandidateModel = hiringCandidateService.deleteHiringCandidate(id, token);
		Response response = new Response("HiringCandidate deleted successfully", 200, hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
