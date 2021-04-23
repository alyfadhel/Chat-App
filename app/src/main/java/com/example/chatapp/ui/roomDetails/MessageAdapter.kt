package com.example.chatapp.ui.roomDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.databinding.LayoutMessageRecievedBinding
import com.example.chatapp.databinding.LayoutMessageSentBinding
import com.example.chatapp.model.dataBase.Message
import com.example.chatapp.ui.Data

class MessageAdapter(var messageList: MutableList<Message>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    val SENT_MESSAGE_TYPE = 0
    val RECEIVE_MESSAGE_TYPE = 1
    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        if (message.senderId.equals(Data.user?.id)) {
            return SENT_MESSAGE_TYPE
        }
        return RECEIVE_MESSAGE_TYPE

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        if (viewType == SENT_MESSAGE_TYPE) {
            val viewHolder: LayoutMessageSentBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_message_sent, parent, false
            )
            return SentMessageViewHolder(viewHolder)
        }
        val viewHolder: LayoutMessageRecievedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_message_recieved, parent, false
        )
        return ReceiveMessageViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addMessages(addedMessages: MutableList<Message>) {
        val oldMessagesCount = messageList.size
        messageList.addAll(addedMessages)
        notifyItemRangeChanged(oldMessagesCount,addedMessages.size)
    }

    abstract class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        abstract fun bind(message: Message)
    }
    class SentMessageViewHolder(val binding: LayoutMessageSentBinding)
        :MessageViewHolder(binding.root){
        override fun bind(message: Message) {
            binding.setMessage(message)
//            binding.executePendingBindings()
            binding.invalidateAll()
        }
    }
    class ReceiveMessageViewHolder(val binding: LayoutMessageRecievedBinding)
        :MessageViewHolder(binding.root){
        override fun bind(message: Message) {
            binding.setMessage(message)
            binding.executePendingBindings()
        }
    }

}