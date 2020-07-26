package com.dinhpu.m4casestudy.services.user;

import com.dinhpu.m4casestudy.dto.user.CrmUser;
import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserServices extends UserDetailsService {
    public User findUserByEmail(String email);
    public User findUserByUserName(String userName);
    public List<User> findAll();
    public User findById(Long id);
    public User save(CrmUser model);
    public User save(User user);
    public User remove(Long id);

}
