package com.mango.customer.controller;

import com.mango.customer.constant.ExceptionMessageConstants;
import com.mango.customer.dto.UserDTO;
import com.mango.customer.exception.NotFoundException;
import com.mango.customer.mocks.user.UserMocks;
import com.mango.customer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@WebMvcTest(UserController.class)
class UserControllerTest {

  private static final String GET_ALL_USERS_ENDPOINT = "/user";
  private static final String GET_USER_ENDPOINT = "/user/email@email.com";
  private static final String CREATE_USER_ENDPOINT = "/signin";
  private static final String UPDATE_USER_ENDPOINT = "/updateUser/email@email.com";

  @Autowired private MockMvc mockMvc;

  @MockBean private UserService userService;

  @Test
  void whenGetAll_shouldReturn200AndUserList() throws Exception {
    List<UserDTO> userDTOList = UserMocks.getUserDTOList();
    when(userService.getAll()).thenReturn(userDTOList);
    ResultActions response = mockMvc.perform(get(GET_ALL_USERS_ENDPOINT));
    verify(userService).getAll();

    response.andExpect(status().isOk()).andDo(print());
  }

  @Test
  void whenGetUser_shouldReturn200AndUser() throws Exception {
    UserDTO user = UserMocks.getUserDTO();
    when(userService.getUser(anyString())).thenReturn(user);
    ResultActions response = mockMvc.perform(get(GET_USER_ENDPOINT));
    verify(userService).getUser(anyString());

    response.andExpect(status().isOk()).andDo(print());
  }

  @Test
  void whenGetUserAndNotFound_shouldReturn404() throws Exception {
    when(userService.getUser(anyString()))
        .thenThrow(new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND));
    ResultActions response = mockMvc.perform(get(GET_USER_ENDPOINT));
    verify(userService).getUser(anyString());

    response.andExpect(status().isNotFound()).andDo(print());
  }

  @Test
  void whenCreateUser_shouldReturn200AndUser() throws Exception {
    UserDTO user = UserMocks.getUserDTO();
    when(userService.createUser(any())).thenReturn(user);
    ResultActions response =
        mockMvc.perform(
            post(CREATE_USER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content("{}"));
    verify(userService).createUser(any());

    response.andExpect(status().isOk()).andDo(print());
  }

  @Test
  void whenUpdateUser_shouldReturn200AndUser() throws Exception {
    UserDTO user = UserMocks.getUserDTO();
    when(userService.updateUser(any(), anyString())).thenReturn(user);
    ResultActions response =
        mockMvc.perform(
            put(UPDATE_USER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content("{}"));
    verify(userService).updateUser(any(), anyString());

    response.andExpect(status().isOk()).andDo(print());
  }

  @Test
  void whenUpdateUserAndNotFound_shouldReturn404() throws Exception {
    when(userService.updateUser(any(), anyString()))
        .thenThrow(new NotFoundException(ExceptionMessageConstants.USER_NOT_FOUND));
    ResultActions response =
        mockMvc.perform(
            put(UPDATE_USER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content("{}"));
    verify(userService).updateUser(any(), anyString());

    response.andExpect(status().isNotFound()).andDo(print());
  }
}
