package com.example.chatapp.ui.roomDetails

import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.dao.MessagesDao
import com.example.chatapp.model.dataBase.Message
import com.example.chatapp.ui.Data
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Timestamp

class RoomDetailsViewModel: BaseViewModel<Navigator>() {

    val messageField = ObservableField<String>()
    var roomId: String?=null

    fun sendMessage(){
        if (!validMessage())return
        val messageObj = Message(messageText = messageField.get(),
        senderId = Data.user?.id,
        senderName = Data.user?.userName,
        roomId = roomId ,
        time = Timestamp.now()
            )
        MessagesDao.sendMessages(messageObj, OnSuccessListener {
          messageField.set("")
        },
            OnFailureListener {
                messageLiveData.value = it.localizedMessage
            })
    }

    private fun validMessage(): Boolean {
        if (messageField.get().isNullOrBlank()){
            return false
        }
        return true

    }
}