package com.mango.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {

  private String name;
  private String lastName;
  private String address;
  private String city;
  @Id
  private String email;

  @OneToMany(mappedBy = "user")
  private List<SloganEntity> slogans;
}
