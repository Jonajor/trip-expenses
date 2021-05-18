package com.split.expenses.domain.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
}
