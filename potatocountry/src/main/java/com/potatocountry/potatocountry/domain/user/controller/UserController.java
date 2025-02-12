package com.potatocountry.potatocountry.domain.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
}
