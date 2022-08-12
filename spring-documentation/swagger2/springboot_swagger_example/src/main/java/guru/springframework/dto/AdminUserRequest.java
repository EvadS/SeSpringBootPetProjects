package guru.springframework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "Admin user request model.")
public class AdminUserRequest {

    @Email
    @NotBlank
    @ApiModelProperty(notes = "Email for new admin user.")
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "Password for new admin user.")
    private String password;

    @NotNull
    @ApiModelProperty(notes = "Admin user role.")
    private int role;

    public AdminUserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
