package com.system.notification.Utils;

import com.system.notification.Constants.CommonConstants;
import com.system.notification.Repository.CommonCrudRepo;
import com.system.notification.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUtils {
    public static final Logger logger = LoggerFactory.getLogger(UserUtils.class);

    @Autowired
    private CommonCrudRepo<User> usersCommonCrudRepo;

    public User getUsersDetailsByName(String email) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(CommonConstants.EMAIL, email);
        List<User> users = usersCommonCrudRepo.findByProperties(queryMap);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
