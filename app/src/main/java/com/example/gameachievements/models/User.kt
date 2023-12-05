package com.example.gameachievements.models

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo val username: String,
    @ColumnInfo val password: String,
    @ColumnInfo val desc: String,
    @ColumnInfo val email: String,
    @ColumnInfo val level: String
)