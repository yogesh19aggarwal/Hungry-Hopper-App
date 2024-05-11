package com.example.hungryhopper.Model

data class UserModel(
    val name: String ?= null,
    val email: String ?= null,
    val password: String ?= null,
    val phoneNum:String ?= null,
    val address:String ?= null
)
