package com.greenfox.programmerspetclub.services.userservice;

import com.greenfox.programmerspetclub.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private User user;

  public UserServiceImpl() {
    this.user = new User();
  }

  @Override
  public User getUser() {
    return this.user;
  }
}
