package com.untone.timehub

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addBtn = findViewById<TextView>(R.id.textView6)
        val darkenBg = findViewById<CardView>(R.id.bgDark)
        val addNewTimeZoneCard = findViewById<CardView>(R.id.addFore)
        val btnCl = findViewById<Button>(R.id.buttonClose)
        val plusBtn = findViewById<ImageView>(R.id.imageView3)
        val utcBar = findViewById<SeekBar>(R.id.seekBar)
        utcBar.setOnSeekBarChangeListener(mover())
        plusBtn.setOnClickListener {
            darkenBg.visibility = View.VISIBLE
            darkenBg.animate().alpha(0.5f).setDuration(1000)
            addNewTimeZoneCard.visibility = View.VISIBLE
            addNewTimeZoneCard.animate().scaleY(-1f).alpha(1.0f)
        }
        addNewTimeZoneCard.rotationX = -180f
        addBtn.setOnClickListener{
            darkenBg.visibility = View.VISIBLE
            darkenBg.animate().alpha(0.5f).setDuration(1000)
            addNewTimeZoneCard.visibility = View.VISIBLE
            addNewTimeZoneCard.animate().scaleY(-1f).alpha(1.0f)
        }
        btnCl.setOnClickListener{
            darkenBg.animate().alpha(0.0f).setDuration(1000)
            addNewTimeZoneCard.animate().scaleY(1f).alpha(0.0f)

        }
    }

    fun mover(): SeekBar.OnSeekBarChangeListener? {
        val utcBar = findViewById<SeekBar>(R.id.seekBar)
        val value = findViewById<TextView>(R.id.textView9)
        utcBar.progress -= 13
        value.text = utcBar.progress.toString()
        return null
    }
}

