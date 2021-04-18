package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.model.dataBase.Room
import com.example.chatapp.ui.roomDetails.RoomDetailsActivity
import com.example.chatapp.ui.room.AddRoomActivity

class HomeActivity :BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {
    lateinit var roomAdapter: RoomAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        setupView()
        observableLivData()
    }

     fun observableLivData() {
        viewModel.roomLiveData.observe(this, Observer {
            roomAdapter.changeData(it)
        })
    }
    override fun onStart() {
        super.onStart()
        viewModel.getRooms()
    }

    private fun setupView() {
        roomAdapter = RoomAdapter(listOf())
        viewDataBinding.recyclerView.adapter = roomAdapter
        roomAdapter.onItemClickListener = object :RoomAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, room: Room) {
               val intent = Intent(this@HomeActivity, RoomDetailsActivity::class.java)
                intent.putExtra("room",room)
                startActivity(intent)
            }
        }

    }

    override fun addList() {
        val intent = Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }
}