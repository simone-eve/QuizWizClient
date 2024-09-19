package com.example.quizwiz3.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/PostLogin")
    fun loginUser(@Body loginModel: LoginModel): Call<LoginResponse>

    @POST("api/PostLogin")
    fun loginWithEmailPassword(@Body loginModel: LoginModel): Call<LoginResponse>

    // For Google SSO login
    @POST("api/PostGoogleLogin")
    fun loginWithGoogle(@Body googleLoginModel: GoogleLoginModel): Call<LoginResponse>
}



data class LoginModel(val email: String, val password: String)
data class LoginResponse(val message: String, val userId: String?, val userRole: String?)
data class GoogleLoginModel(val googleId: String)
data class LoginRequest(val email: String, val password: String)


