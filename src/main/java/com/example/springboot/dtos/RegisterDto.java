package com.example.springboot.dtos;

import com.example.springboot.enums.UserRoleEnum;

public record RegisterDto(String login, String password, UserRoleEnum role) {
}
