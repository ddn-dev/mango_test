package com.mango.customer.controller;

import com.mango.customer.dto.SloganDTO;
import com.mango.customer.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {

	private final CampaignService campaignService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	@RequestMapping("/slogan")
	public ResponseEntity<SloganDTO> slogan(@RequestBody SloganDTO slogan) {
		return ResponseEntity.ok(campaignService.slogan(slogan));
	}
}
