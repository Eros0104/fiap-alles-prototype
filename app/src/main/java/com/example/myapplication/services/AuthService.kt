package com.example.myapplication.services

import com.example.myapplication.models.LoginParameters

private val availableUsers = listOf(
    LoginParameters("katia@email.com", "@senhasegura1!34"),
    LoginParameters("john","123")
);
class AuthService {
    fun login(loginParameters: LoginParameters): Boolean {
        val foundUser = availableUsers.find { user -> user.username == loginParameters.username }

        if (foundUser != null && foundUser.password == loginParameters.password) {
            return true;
        }

        return false;
    }
}