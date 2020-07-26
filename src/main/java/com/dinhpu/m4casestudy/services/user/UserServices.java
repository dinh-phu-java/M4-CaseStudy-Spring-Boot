package com.dinhpu.m4casestudy.services.user;

import com.dinhpu.m4casestudy.dao.user.RoleDAO;
import com.dinhpu.m4casestudy.dao.user.UserDAO;
import com.dinhpu.m4casestudy.dto.user.CrmUser;
import com.dinhpu.m4casestudy.model.user.Role;
import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices implements IUserServices{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        Optional<User> userOption=userDAO.findById(id);
        User findUser=null;
        if (userOption.isPresent()){
            findUser=userOption.get();
        }
        return findUser;
    }

    @Override
    @Transactional
    public User save(CrmUser crmUser) {
        User user = new User();
        // assign user details to the user object
        user.setUserName(crmUser.getEmail());
        user.setPassword(passwordEncoder.encode(crmUser.getPassword()));

        user.setFullName(crmUser.getFullName());
        user.setEmail(crmUser.getEmail());

        user.setAddress(crmUser.getAddress());
        user.setPhoneNumber(crmUser.getPhoneNumber());

        user.setLogoUrl("/dist/img/user-default.png");
        // give user default role of "employee"
        user.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_USER")));

        // save user in the database
       return userDAO.save(user);

//        return userDAO.save();
    }

    @Override
    @Transactional
    public User save(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public User remove(Long id) {
        User deleteUser= this.findById(id);
        userDAO.deleteById(id);
        return deleteUser;
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    @Transactional
    public User findUserByUserName(String userName) {
        return userDAO.findUserByUserName(userName);
    }



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userDAO.findUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
