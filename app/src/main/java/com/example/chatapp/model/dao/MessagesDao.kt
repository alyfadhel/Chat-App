package com.example.chatapp.model.dao

import android.os.Message
import com.example.chatapp.model.dataBase.DataBase
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference

class MessagesDao {
    companion object{
        fun sendMessages(message: com.example.chatapp.model.dataBase.Message,onSuccessListener: OnSuccessListener<Void>,
                         onFailureListener: OnFailureListener){
            val room = DataBase.getRoomsCollection()
                .document(message.roomId?:"")



            val messages = room.collection("messages")




            val newMessages = messages.document()
            message.id = newMessages.id
            newMessages.set(message)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }
        fun getMessageRef(roomId: String): CollectionReference {
            return DataBase.getRoomsCollection().document(roomId)
                    .collection("messages")
        }
    }
}