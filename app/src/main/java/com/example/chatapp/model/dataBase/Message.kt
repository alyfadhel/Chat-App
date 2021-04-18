package com.example.chatapp.model.dataBase

import com.google.firebase.Timestamp

data class Message(var id: String?=null,
                   var messageText: String?=null,
                   var senderName: String?=null,
                   var senderId: String?=null,
                   var roomId: String?=null,
                   var time: Timestamp?=null
)
