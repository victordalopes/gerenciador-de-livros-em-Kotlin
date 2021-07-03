package com.example.livrosemkotlin.beans

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "livro")
data class Livro(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var titulo: String = "",
    var autor: String = "",
    var editora: String = ""
): Parcelable