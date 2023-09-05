package com.example.mtgcommanderachievements.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Achievement(@PrimaryKey val id:Int,
                       @ColumnInfo("name") val name:String,
                       @ColumnInfo("desc") val desc: String,
                       @ColumnInfo("points") val points: Int,
                       @ColumnInfo("completed") val completed: Boolean
    ) {
}