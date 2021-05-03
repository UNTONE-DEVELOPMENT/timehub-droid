package com.untone.timehub

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.login)
        val loginBtn = findViewById<Button>(R.id.logInBtn)
        loginBtn.setOnClickListener {
            val mainact = Intent(this, MainActivity::class.java)
            startActivity(mainact)
        }
    }

}