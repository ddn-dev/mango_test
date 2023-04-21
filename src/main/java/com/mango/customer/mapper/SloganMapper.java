package com.mango.customer.mapper;

import com.mango.customer.dto.SloganDTO;
import com.mango.customer.model.SloganEntity;
import org.mapstruct.*;

@Mapper(
	componentModel = MappingConstants.ComponentModel.SPRING,
	unmappedSourcePolicy = ReportingPolicy.IGNORE,
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	builder = @Builder(disableBuilder = true))
public interface SloganMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "text", target = "text")
	@Mapping(source = "user.email", target = "userEmail")
	SloganDTO toDTO(SloganEntity slogan);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "text", target = "text")
	@Mapping(source = "userEmail", target = "user.email")
	SloganEntity toEntity(SloganDTO slogan);
}
