package com.mango.customer.service;

import com.mango.customer.dto.UserDTO;

import java.util.List;

public interface UserService {

	List<UserDTO> getAll();
	UserDTO getUser(String email);
	UserDTO createUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO userDTO, String email);
}
