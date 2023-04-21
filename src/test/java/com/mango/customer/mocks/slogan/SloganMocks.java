package com.mango.customer.mocks.slogan;

import com.mango.customer.dto.SloganDTO;

public class SloganMocks {

  private static final String SLOGAN_MESSAGE = "Slogan message";
  private static final String USER_EMAIL = "j@email.com";

  public static SloganDTO getSlogan() {
    return SloganDTO.builder().text(SLOGAN_MESSAGE).userEmail(USER_EMAIL).build();
  }
}
