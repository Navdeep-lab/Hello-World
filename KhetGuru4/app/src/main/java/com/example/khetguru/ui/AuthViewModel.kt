package com.example.khetguru.ui

import android.os.Message
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {
    private val auth: FirebaseAuth=FirebaseAuth.getInstance()
    private val _authState=MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
    init {
        checkAuthStatus()
    }
    fun checkAuthStatus()
    {
        if(auth.currentUser==null){
            _authState.value=AuthState.Unauthenticated
        }
        else{
            _authState.value=AuthState.Authenticated
        }
    }
    fun login(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            _authState.value=AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value=AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                    task->
                if(task.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }else{
                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }
    fun signup(email:String,password:String,confirmPassword:String){
        if(email.isEmpty()||password.isEmpty()){
            _authState.value=AuthState.Error("Email or password can't be empty")
            return
        }
        if (password != confirmPassword) {
            _authState.value = AuthState.Error("Passwords do not match")
            return
        }
        _authState.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                    task->
                if(task.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }else{
                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }
    fun signout(){
        auth.signOut()
        _authState.value=AuthState.Unauthenticated
    }
    fun resetPassword(email: String) {
        if (email.isEmpty()) {
            _authState.value = AuthState.Error("Email can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                _authState.value = AuthState.Success("Password reset email sent successfully.")
            }
            .addOnFailureListener { e ->
                _authState.value = AuthState.Error(e.message ?: "Failed to send reset email.")
            }
    }


}
sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
    data class Success(val message: String) : AuthState()
}