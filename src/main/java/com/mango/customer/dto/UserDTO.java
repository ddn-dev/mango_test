package com.mango.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String name;
	private String lastName;
	private String address;
	private String city;
	private String email;
	private List<SloganDTO> slogans;
}
