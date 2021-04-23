package com.example.chatapp.ui.roomDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityRoomDetailsBinding
import com.example.chatapp.model.dao.MessagesDao
import com.example.chatapp.model.dataBase.Message
import com.example.chatapp.model.dataBase.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class RoomDetailsActivity : BaseActivity<ActivityRoomDetailsBinding,RoomDetailsViewModel>() {
    var room: Room?=null
    lateinit var messageAdapter: MessageAdapter
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
        initRecyclerView()
        subscribeToRoomMessages(room?.id?:"")
    }

    private fun subscribeToRoomMessages(roomId: String) {
        if (roomId!=null) {

            MessagesDao.getMessageRef(roomId)
                    .orderBy("time", Query.Direction.ASCENDING)
                    .addSnapshotListener { snapshots, e ->
                        if (e != null) {
                            showDialoge(message = e.localizedMessage)
                            return@addSnapshotListener
                        }
                        val addedMessages = mutableListOf<Message>()
                        for (dc in snapshots!!.documentChanges) {
                            when (dc.type) {
                                DocumentChange.Type.ADDED -> {
                                    val newMessage = dc.document.toObject(Message::class.java)
                                    addedMessages.add(newMessage)
                                }
//                            Log.d(TAG, "New city: ${dc.document.data}")
//                        DocumentChange.Type.REMOVED -> Log.d(TAG, "Removed city: ${dc.document.data}")
                            }
                        }
                        messageAdapter.addMessages(addedMessages)
                        viewDataBinding.recyclerViewMsg.smoothScrollToPosition(messageAdapter.messageList.size )
                    }
        }
    }

    private fun initRecyclerView() {
        messageAdapter = MessageAdapter(mutableListOf<Message>())
        viewDataBinding.recyclerViewMsg.adapter = messageAdapter
    }
}