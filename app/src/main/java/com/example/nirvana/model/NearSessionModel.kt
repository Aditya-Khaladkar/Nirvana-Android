package com.example.nirvana.model

data class NearSessionModel(
    val title: String,
    val eventArea: String,
    val imageLink: String,
    val eventDescription: String,
    val eventDate: String,
    val eventTime: String,
    val eventAddress: String
) {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}