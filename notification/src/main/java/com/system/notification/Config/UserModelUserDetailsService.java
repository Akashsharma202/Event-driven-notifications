package com.system.notification.Config;

import com.system.notification.Constants.CommonConstants;
import com.system.notification.Repository.CommonCrudRepo;
import com.system.notification.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserModelUserDetailsService implements UserDetailsService {
    @Autowired
    private CommonCrudRepo<User> usersCommonCrudRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(CommonConstants.EMAIL, username);
        List<User> users = usersCommonCrudRepo.findByProperties(queryMap);
        if (users.size() > 0) {
            // If the user is found, create and return a UserDetails object based on the user
            return new UserModelUserDetails(users.get(0), username);
        } else {
            // If the user is not found, throw an exception indicating the username was not found
            throw new UsernameNotFoundException("Username not found: " + username);
        }
    }
}
