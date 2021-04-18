package com.example.chatapp.model.dataBase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(var id: String?=null,
                val name: String?=null,
                val desc: String?=null):Parcelable
