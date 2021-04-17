package com.example.chatapp.model.dao

import com.example.chatapp.model.dataBase.DataBase
import com.example.chatapp.model.dataBase.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot

class UserDao {
    companion object{
        fun addUser(user: User, onCompleteListener: OnCompleteListener<Void>){
            DataBase.getUserCollection().document(user.id?:"")
                .set(user).addOnCompleteListener(onCompleteListener)
        }
        fun getUser(userId: String, onCompleteListener: OnCompleteListener<DocumentSnapshot>){
            DataBase.getUserCollection().document(userId)
                .get().addOnCompleteListener(onCompleteListener)
        }
    }
}