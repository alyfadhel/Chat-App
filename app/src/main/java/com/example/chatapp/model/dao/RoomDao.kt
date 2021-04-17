package com.example.chatapp.model.dao

import com.example.chatapp.model.dataBase.DataBase
import com.example.chatapp.model.dataBase.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot

class RoomDao {
    companion object{
        fun addRoom(room: Room, onCompleteListener: OnCompleteListener<Void>){
            val document = DataBase.getRoomCollection().document()
            room.id = document.id
            document.set(room).addOnCompleteListener(onCompleteListener)
        }
        fun getRooms(onSuccessListener: OnSuccessListener<QuerySnapshot>, onFailureListener: OnFailureListener){
            DataBase.getRoomCollection()
                    .get()
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(onFailureListener)
        }
    }
}