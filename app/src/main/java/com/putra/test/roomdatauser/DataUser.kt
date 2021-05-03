package com.putra.test.roomdatauser

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "datauser")
@Parcelize
data class DataUser (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "username")
    var username:String,

    @ColumnInfo(name = "role")
    var role:String,

    @ColumnInfo(name = "email")
    var email:String?,

    @ColumnInfo(name = "password")
    var password:String?
):Parcelable
