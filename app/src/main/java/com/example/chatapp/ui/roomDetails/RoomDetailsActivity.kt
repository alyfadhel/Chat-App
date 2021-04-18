package com.example.chatapp.ui.roomDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityRoomDetailsBinding
import com.example.chatapp.model.dataBase.Room

class RoomDetailsActivity : BaseActivity<ActivityRoomDetailsBinding,RoomDetailsViewModel>() {
    var room: Room?=null
    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }

    override fun initializeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        room = intent.getParcelableExtra("room")
        viewModel.roomId = room?.id
    }
}