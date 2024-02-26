package com.example.Blogapi.payload;

import lombok.Data;
@Data
public class LoginDto {
private String usernameOrEmail;
private String password;
}
