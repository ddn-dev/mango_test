package com.mango.customer.mocks.user;

import com.mango.customer.dto.UserDTO;
import com.mango.customer.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserMocks {

  private static final String USER_NAME = "Juan";
  private static final String USER_LAST_NAME = "Jimenez";
  private static final String USER_EMAIL = "j@email.com";

  public static List<UserDTO> getUserDTOList() {
    List<UserDTO> users = new ArrayList<>();
    users.add(
        UserDTO.builder().name(USER_NAME).lastName(USER_LAST_NAME).address(USER_EMAIL).build());
    return users;
  }

  public static UserDTO getUserDTO() {
    return UserDTO.builder().name(USER_NAME).lastName(USER_LAST_NAME).address(USER_EMAIL).build();
  }

  public static List<UserEntity> getUserEntityList() {
    List<UserEntity> users = new ArrayList<>();
    users.add(
        UserEntity.builder().name(USER_NAME).lastName(USER_LAST_NAME).address(USER_EMAIL).build());
    return users;
  }

  public static UserEntity getUserEntity() {
    return UserEntity.builder()
        .name(USER_NAME)
        .lastName(USER_LAST_NAME)
        .address(USER_EMAIL)
        .build();
  }
}
