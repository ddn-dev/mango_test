package com.mango.customer.service.impl;

import com.mango.customer.constant.ExceptionMessageConstants;
import com.mango.customer.dto.SloganDTO;
import com.mango.customer.exception.NotFoundException;
import com.mango.customer.exception.MaxSloganLimitReachedException;
import com.mango.customer.mapper.SloganMapper;
import com.mango.customer.model.SloganEntity;
import com.mango.customer.model.UserEntity;
import com.mango.customer.repository.SloganRepository;
import com.mango.customer.repository.UserRepository;
import com.mango.customer.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

  private static int MAX_SLOGANS_QUANTITY = 3;

  private final SloganRepository sloganRepository;
  private final UserRepository userRepository;
  private final SloganMapper sloganMapper;

  @Override
  public SloganDTO slogan(SloganDTO slogan) {
    Optional<UserEntity> userOp = userRepository.findById(slogan.getUserEmail());
    if (userOp.isPresent()) {
      UserEntity userSaved = userOp.get();
      if (userSaved.getSlogans().size() < MAX_SLOGANS_QUANTITY) {
        SloganEntity sloganToSave = sloganMapper.toEntity(slogan);
        return sloganMapper.toDTO(sloganRepository.save(sloganToSave));
      }
      throw new MaxSloganLimitReachedException();
    }
    throw new NotFoundException(
        String.format(ExceptionMessageConstants.USER_NOT_FOUND, slogan.getUserEmail()));
  }
}
