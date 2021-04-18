package com.example.chatapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.databinding.LayoutRoomBinding
import com.example.chatapp.model.dataBase.Room

class RoomAdapter(var roomList: List<Room>): RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding:LayoutRoomBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.layout_room,parent,false)
        return ViewHolder(itemBinding)

    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = roomList[position]
        holder.bind(room)
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,room)
            }
        }

    }

    var onItemClickListener: OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position: Int,room: Room)
    }

    override fun getItemCount(): Int =
            roomList.size

    class ViewHolder(var itemBinding: LayoutRoomBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(room: Room){
            itemBinding.room = room
        }
    }
    fun changeData(roomList: List<Room>){
        this.roomList =roomList
        notifyDataSetChanged()
    }
}