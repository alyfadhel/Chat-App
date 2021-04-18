package com.example.chatapp.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.dao.UserDao
import com.example.chatapp.model.dataBase.User
import com.example.chatapp.ui.Data
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<Boolean>(false)
    val passwordError = ObservableField<Boolean>(false)

    val firebaseAuth = Firebase.auth

        fun login(){

          if (!valid())return
            firebaseAuth.signInWithEmailAndPassword(email.get(),password.get())
                .addOnCompleteListener(OnCompleteListener { task ->
                    showLoading.value = false
                    if (task.isSuccessful)
                        getUserInDB(firebaseAuth.currentUser.uid)
                    else
                        messageLiveData.value = task.exception?.localizedMessage
                })
        }

    private fun getUserInDB(userId: String) {
        UserDao.getUser(userId, OnCompleteListener { task ->
            if (task.isSuccessful){
                val user = task.result?.toObject(User::class.java)
                navigator?.goToHomeActivity()
                Data.user = user
            }else{

                messageLiveData.value = task.exception?.localizedMessage
            }
        })

    }

    private fun valid(): Boolean {
        var isValid = true
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid = false
            }else{
             emailError.set(false)
        }
        if (password.get().isNullOrBlank()||password.get()?.length?:0<6){
            passwordError.set(true)
            isValid = false
        }else{
            passwordError.set(false)
        }
        return isValid
    }

    fun goToRegister(){
        navigator?.goToRegister()
    }

}