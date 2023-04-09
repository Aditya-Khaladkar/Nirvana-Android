package com.example.nirvana.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseAPI {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
}