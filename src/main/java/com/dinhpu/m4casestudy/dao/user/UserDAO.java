package com.dinhpu.m4casestudy.dao.user;

import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
    public User findUserByEmail(String email);
    public User findUserByUserName(String userName);
}
