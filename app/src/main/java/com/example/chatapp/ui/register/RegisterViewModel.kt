package com.example.chatapp.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.dao.UserDao
import com.example.chatapp.model.dataBase.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel: BaseViewModel<Navigator>() {

    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val userName = ObservableField<String>()
    val password = ObservableField<String>()
    val nameError = ObservableField<Boolean>(false)
    val emailError = ObservableField<Boolean>(false)
    val userNameError = ObservableField<Boolean>(false)
    val passwordError = ObservableField<Boolean>(false)

    val firebaseAuth = Firebase.auth


    fun register(){
        if (!valid())return
        firebaseAuth.createUserWithEmailAndPassword(email.get(),password.get())
            .addOnCompleteListener(OnCompleteListener { task ->
                if (task.isSuccessful){
                    val firebaseUser = firebaseAuth.currentUser
                    val user = User(firebaseUser.uid, name.get(),userName.get(),email.get())
                    addUsersInDB(user)
                }else{
                    messageLiveData.value = task.exception?.localizedMessage
                }
            })
    }

    private fun addUsersInDB(user: User) {
        UserDao.addUser(user, OnCompleteListener { task ->
            if (task.isSuccessful){
                navigator?.goToHomeActivity()
            }else{
                messageLiveData.value = task.exception?.localizedMessage
            }
        })
    }

    private fun valid(): Boolean {
        var isValid = true
        if (name.get().isNullOrBlank()){
            nameError.set(true)
            isValid = false
        }else{
            nameError.set(false)
        }
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid = false
        }else{
            emailError.set(false)
        }
        if (userName.get().isNullOrBlank()){
            userNameError.set(true)
            isValid = false
        }else{
            userNameError.set(false)
        }
        if (password.get().isNullOrBlank()||password.get()?.length?:0<6){
            passwordError.set(true)
            isValid = false
        }else{
            passwordError.set(false)
        }
        return isValid

    }

}