package com.mango.customer.mapper;

import com.mango.customer.dto.UserDTO;
import com.mango.customer.model.UserEntity;
import org.mapstruct.*;

@Mapper(
	componentModel = MappingConstants.ComponentModel.SPRING,
	unmappedSourcePolicy = ReportingPolicy.IGNORE,
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	builder = @Builder(disableBuilder = true))
public interface UserMapper {

	@Mapping(source = ".", target = ".")
	UserDTO toDTO(UserEntity userEntity);

	@Mapping(source = ".", target = ".")
	UserEntity toEntity(UserDTO userDTO);
}
