package com.mango.customer.service;

import com.mango.customer.dto.UserDTO;
import com.mango.customer.exception.NotFoundException;
import com.mango.customer.mapper.UserMapper;
import com.mango.customer.mocks.user.UserMocks;
import com.mango.customer.model.UserEntity;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceTests {

  @InjectMocks private UserServiceImpl userService;

  @Mock private UserMapper userMapper;

  @Mock private UserRepository userRepository;

  @BeforeEach
  void init() {
    userService = new UserServiceImpl(userRepository, userMapper);
  }

  @Test
  void whenGetAllsUsers_shouldReturnUserList() {
    List<UserEntity> users = UserMocks.getUserEntityList();
    when(userRepository.findAll()).thenReturn(users);
    List<UserDTO> usersResult = userService.getAll();
    verify(userRepository).findAll();

    assertThat(usersResult, is(not(empty())));
  }

  @Test
  void whenGetUser_shouldReturnUser() {
    UserEntity userEntity = UserMocks.getUserEntity();
    UserDTO userDTO = UserMocks.getUserDTO();
    when(userRepository.findById(anyString())).thenReturn(Optional.of(userEntity));
    when(userMapper.toDTO(any())).thenReturn(userDTO);

    UserDTO userResult = userService.getUser("");

    verify(userRepository).findById(anyString());
    verify(userMapper).toDTO(any());

    assertThat(userResult, is(not(nullValue())));
  }

  @Test
  void whenGetUserAndReturnEmpty_shouldThrowNotFoundException() {
    when(userRepository.findById(anyString())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.getUser(""));
    verify(userRepository).findById(anyString());
  }

  @Test
  void whenCreateUser_shouldReturnUser() {
    UserEntity userEntity = UserMocks.getUserEntity();
    UserDTO userDTO = UserMocks.getUserDTO();

    when(userRepository.save(any())).thenReturn(userEntity);
    when(userMapper.toDTO(any())).thenReturn(userDTO);
    when(userMapper.toEntity(any())).thenReturn(userEntity);

    UserDTO userResult = userService.createUser(userDTO);

    verify(userRepository).save(any());
    verify(userMapper).toDTO(any());
    verify(userMapper).toEntity(any());

    assertThat(userResult, is(not(nullValue())));
  }

  @Test
  void whenUpdateUser_shouldReturnUser() {
    UserEntity userEntity = UserMocks.getUserEntity();
    UserDTO userDTO = UserMocks.getUserDTO();

    when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
    when(userMapper.toDTO(any())).thenReturn(userDTO);

    UserDTO userResult = userService.updateUser(userDTO, "");

    verify(userRepository).findById(any());
    verify(userMapper).toDTO(any());

    assertThat(userResult, is(not(nullValue())));
  }

  @Test
  void whenUpdateUserAndNotFound_shouldThrowNotFoundException() {
    UserDTO userDTO = UserMocks.getUserDTO();

    when(userRepository.findById(any())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userService.updateUser(userDTO, ""));

    verify(userRepository).findById(any());
  }
}
