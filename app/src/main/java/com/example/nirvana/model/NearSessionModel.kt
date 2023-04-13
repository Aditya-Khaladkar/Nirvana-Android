package com.example.nirvana.model

data class NearSessionModel(
    val title: String,
    val eventArea: String,
    val imageLink: String
) {
    constructor() : this("", "", "")
}