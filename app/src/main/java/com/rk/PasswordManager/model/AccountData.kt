package com.rk.PasswordManager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AccountData")

data class AccountData(
    @PrimaryKey(autoGenerate = true)
    val AccountId: Int = 0,
    val accountName: String,
    val userName: String,
    val password: String

)

