package com.mango.customer.controller;

import com.mango.customer.dto.SloganDTO;
import com.mango.customer.exception.MaxSloganLimitReachedException;
import com.mango.customer.mocks.slogan.SloganMocks;
import com.mango.customer.service.CampaignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CampaignController.class)
class CampaignControllerTest {

  private static final String CAPAIGN_BASE_PATH = "/campaigns";
  private static final String CREATE_SLOGAN_ENDPOINT = CAPAIGN_BASE_PATH + "/slogan";

  @Autowired private MockMvc mockMvc;

  @MockBean private CampaignService campaignService;

  @Test
  void whenCreateSlogan_shouldReturn200() throws Exception {
    SloganDTO slogan = SloganMocks.getSlogan();
    when(campaignService.slogan(any())).thenReturn(slogan);
    ResultActions response =
        mockMvc.perform(
            post(CREATE_SLOGAN_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content("{}"));
    verify(campaignService).slogan(any());

    response.andExpect(status().isOk()).andDo(print());
  }

  @Test
  void whenCreateSloganAndMaxLimitReached_shouldReturn405() throws Exception {
    when(campaignService.slogan(any())).thenThrow(new MaxSloganLimitReachedException());
    ResultActions response =
        mockMvc.perform(
            post(CREATE_SLOGAN_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content("{}"));
    verify(campaignService).slogan(any());

    response.andExpect(status().isNotAcceptable()).andDo(print());
  }
}
