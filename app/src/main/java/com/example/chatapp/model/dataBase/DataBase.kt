package com.example.chatapp.model.dataBase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

object DataBase {
   private val database = FirebaseFirestore.getInstance()
    const val USER_PATH = "users"
    fun getUserCollection(): CollectionReference {
        return database.collection(USER_PATH)
    }
    const val Room_PATH = "rooms"
    fun getRoomsCollection(): CollectionReference {
        return database.collection(Room_PATH)
    }
}