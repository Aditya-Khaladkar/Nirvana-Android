package com.example.nirvana

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nirvana.databinding.ActivityMainBinding
import com.example.nirvana.util.ToastMessage

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var pressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            ToastMessage.show(this, "Press back again to exit")
        }
        pressedTime = System.currentTimeMillis();
    }
}