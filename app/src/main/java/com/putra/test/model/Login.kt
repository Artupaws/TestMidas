package com.putra.test.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login (val email:String, val password:String):Parcelable