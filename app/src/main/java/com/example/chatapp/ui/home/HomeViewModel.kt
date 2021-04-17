package com.example.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.dao.RoomDao
import com.example.chatapp.model.dataBase.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class HomeViewModel: BaseViewModel<Navigator>() {
    val roomLiveData = MutableLiveData<List<Room>>()
    fun getRooms(){
        RoomDao.getRooms(OnSuccessListener {result->
            val roomList: MutableList<Room> = mutableListOf()
            for (document in result){
                val room = document.toObject(Room::class.java)
                roomList.add(room)
            }
            roomLiveData.value = roomList
        },


                OnFailureListener {
                    messageLiveData.value = it.localizedMessage
                }
                )
    }

    fun goToRoomList(){
        navigator?.addList()
    }
}