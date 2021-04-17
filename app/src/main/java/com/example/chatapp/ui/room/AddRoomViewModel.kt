package com.example.chatapp.ui.room

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.dao.RoomDao
import com.example.chatapp.model.dataBase.Room
import com.google.android.gms.tasks.OnCompleteListener

class AddRoomViewModel: BaseViewModel<Navigator>() {

    val roomName = ObservableField<String>()
    val roomDesc = ObservableField<String>()
    val roomAdded = MutableLiveData<Boolean>()

    fun addRoom(){
        if (!valid())return
        val room = Room(name = roomName.get(), desc = roomDesc.get())
        RoomDao.addRoom(room, OnCompleteListener { task ->
            if (task.isSuccessful){
               roomAdded.value = true
            }else{
                messageLiveData.value = task.exception?.localizedMessage
            }
        })
    }

    private fun valid(): Boolean {
        if (roomName.get().isNullOrBlank()){
            messageLiveData.value = "Please Enter Room Name"
            return false
        }
        if (roomDesc.get().isNullOrBlank()){
            messageLiveData.value = "Please Enter Room Desc"
            return false
        }
             return true
    }
}