package com.mango.customer.service.impl;

import com.mango.customer.constant.ExceptionMessageConstants;
import com.mango.customer.dto.UserDTO;
import com.mango.customer.exception.NotFoundException;
import com.mango.customer.mapper.UserMapper;
import com.mango.customer.model.UserEntity;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public List<UserDTO> getAll() {
    return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public UserDTO getUser(String email) {
    Optional<UserEntity> userOp = userRepository.findById(email);

    if (userOp.isPresent()) {
      return userMapper.toDTO(userOp.get());
    }
    throw new NotFoundException(String.format(ExceptionMessageConstants.USER_NOT_FOUND, email));
  }

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    UserEntity userSaved = userRepository.save(userMapper.toEntity(userDTO));
    return userMapper.toDTO(userSaved);
  }

  @Override
  public UserDTO updateUser(UserDTO userDTO, String email) {
    Optional<UserEntity> userOp = userRepository.findById(email);

    if (userOp.isPresent()) {
      UserEntity userSaved = userOp.get();

      userSaved.setAddress(userDTO.getAddress());
      userSaved.setCity(userDTO.getCity());
      userSaved.setName(userDTO.getName());
      userSaved.setLastName(userDTO.getLastName());

      return userMapper.toDTO(userRepository.save(userSaved));
    }
    throw new NotFoundException(
        String.format(ExceptionMessageConstants.USER_NOT_FOUND, userDTO.getEmail()));
  }
}
