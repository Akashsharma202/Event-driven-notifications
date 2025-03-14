package com.system.notification.Controller;

import com.system.notification.Service.UserService;
import com.system.notification.model.DTO.LoginDTO;
import com.system.notification.model.DTO.ResponseObject;
import com.system.notification.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/access")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Login the system", description = "API to login using email and encrypted password and return access token in return", tags = {"AccessService"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = ResponseObject.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    //@PreAuthorize("hasAnyAuthority('ROLE_SUPER_USER', 'ROLE_UNIT_LEADER', 'ROLE_BSC', 'ROLE_STUDENT', 'ROLE_MENTOR', 'ROLE_GUEST')")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Login the system", description = "API to login using email and encrypted password and return access token in return", tags = {"AccessService"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = ResponseObject.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    //@PreAuthorize("hasAnyAuthority('ROLE_SUPER_USER', 'ROLE_UNIT_LEADER', 'ROLE_BSC', 'ROLE_STUDENT', 'ROLE_MENTOR', 'ROLE_GUEST')")
    public ResponseEntity<ResponseObject> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

}
