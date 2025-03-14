package com.system.notification.Service;

import com.system.notification.Constants.CommonConstants;
import com.system.notification.Repository.CommonCrudRepo;
import com.system.notification.model.DTO.LoginDTO;
import com.system.notification.model.DTO.ResponseObject;
import com.system.notification.model.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    public static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private CommonCrudRepo<User> userCommonCrudRepo;
    @Autowired
    private JwtService jwtService;

    public ResponseObject login(LoginDTO request) {
        ResponseObject responseObject= new ResponseObject();
        Map<String, Object> queryMap= new HashMap<>();
        queryMap.put(CommonConstants.EMAIL, request.getEmail());
        List<User> user= userCommonCrudRepo.findByProperties(queryMap);
        if(user.isEmpty()){
            responseObject.setStatus(false);
            responseObject.setErrorMessage("Can't find user with email: "+request.getEmail());
            return responseObject;
        }
        if(user.get(0).getPassword().equals(request.getPassword())){
            responseObject.setStatus(true);
            Map<String, String> response= new HashMap<>();
            response.put(CommonConstants.ACCESS_TOKEN, jwtService.generateToken(user.get(0).getEmail()));
            response.put(CommonConstants.REFRESH_TOKEN, jwtService.generateRefreshToken(user.get(0).getEmail()));
            responseObject.setJsonData("Authentication Successfully");
            responseObject.setData(response);
            return responseObject;
        }
        responseObject.setStatus(false);
        responseObject.setErrorMessage("Wrong credentials");
        return responseObject;
    }

    public ResponseObject register(User user) {
        ResponseObject responseObject;
        responseObject = checkDuplicateUser(user);
        if(Boolean.FALSE.equals(responseObject.getStatus())){
            return responseObject;
        }
        user.setId(new ObjectId().toString());
        user.setCreatedOn(System.currentTimeMillis());
        user.setUpdatedOn(System.currentTimeMillis());
        User result= userCommonCrudRepo.save(user);
        responseObject.setStatus(true);
        responseObject.setJsonData(result.getId());
        return responseObject;
    }

    private ResponseObject checkDuplicateUser(User user) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(CommonConstants.NAME, user.getName());
        List<User> duplicateNames = userCommonCrudRepo.findByProperties(queryMap);
        if (user.getId() != null && !user.getId().isEmpty()) {
            duplicateNames.removeIf(obj -> obj.getId().equals(user.getId()));
        }
        if (!duplicateNames.isEmpty()) {
            responseObject.setStatus(false);
            responseObject.setJsonData("User Already exists!!.");
            logger.error(" Name : " + user.getName() + " already exists. Please try another.");
            return responseObject;
        }
        queryMap.put(CommonConstants.EMAIL, user.getEmail());
        List<User> duplicateReferences = userCommonCrudRepo.findByProperties(queryMap);
        if (user.getId() != null && !user.getId().isEmpty()) {
            duplicateReferences.removeIf(userObj -> userObj.getId().equals(user.getId()));
        }
        if (!duplicateReferences.isEmpty()) {
            responseObject.setStatus(false);
            responseObject.setJsonData("User with email: "+user.getEmail()+" already exist.");
            return responseObject;
        }
        responseObject.setStatus(true);
        return responseObject;
    }
}
