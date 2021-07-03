package com.example.livrosemkotlin.beans

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "editora")
data class Editora (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var nome: String = "",
    var fundacao: String = "",
    var sede: String = "",
    var cnpj: String = ""
): Parcelable