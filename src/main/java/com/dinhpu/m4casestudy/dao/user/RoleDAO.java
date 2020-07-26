package com.dinhpu.m4casestudy.dao.user;

import com.dinhpu.m4casestudy.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,Integer> {
    public Role findRoleByName(String theRoleName);
}
