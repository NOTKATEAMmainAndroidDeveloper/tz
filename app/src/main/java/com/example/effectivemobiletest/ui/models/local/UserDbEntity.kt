package com.example.effectivemobiletest.ui.models.local

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDbEntity(
    @PrimaryKey(autoGenerate = false) var email: String,
    @ColumnInfo(name = "firstName") var firstName: String,
    var secondName: String?,
    @Nullable var password: String?
)
