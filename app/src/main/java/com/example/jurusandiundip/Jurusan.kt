package com.example.jurusandiundip

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jurusan(
    val name: String,
    val accreditation: String,
    val history: String,
    val topic_learned: String,
    val image: Int,
    val link: String
): Parcelable
