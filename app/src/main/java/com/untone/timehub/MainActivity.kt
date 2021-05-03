package com.untone.timehub

import android.app.ActionBar
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.children
import androidx.core.view.marginLeft
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import java.io.File
import java.lang.Exception
import java.lang.invoke.ConstantCallSite
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addBtn = findViewById<TextView>(R.id.textView6)
        var handler: Handler = Handler()
        val listV = findViewById<ScrollView>(R.id.cardScr)
        var animationDone: Boolean = false //may be userful in the future idk
        val darkenBg = findViewById<CardView>(R.id.bgDark)
        val addNewTimeZoneCard = findViewById<CardView>(R.id.addFore)
        val btnCl = findViewById<Button>(R.id.buttonClose)
        val cardScr = findViewById<LinearLayout>(R.id.linLayCrd)
        val plusBtn = findViewById<ImageView>(R.id.imageView3)
        val utcBar = findViewById<Slider>(R.id.seekBar)
        val descEd = findViewById<TextInputEditText>(R.id.descEd)
        val namEd = findViewById<TextInputEditText>(R.id.namEd)
        val doneBtn = findViewById<Button>(R.id.buttonDoneCrt)
        val scale: Float = applicationContext.getResources().getDisplayMetrics().density
        val mainLay = findViewById<ConstraintLayout>(R.id.clay)
        val texTU = findViewById<TextView>(R.id.textView9)

        listV.setOnClickListener(null)

        handler.postDelayed(Runnable {
            texTU.text = utcBar.value.toString()
            handler.postDelayed(runnable!!, delay.toLong())
        }.also { runnable = it }, delay.toLong())

try {

        val listOfCrdFiles = Files.list(filesDir.toPath()).forEach{
            if (it.toString().contains("cardArray")){
                val reader = File(it.toString()).readText()
                println(reader)
                val gson = Gson()
                val parsed = gson.fromJson(reader, CardJS::class.java)
                var timeCard = CardView(this)
                cardScr.addView(timeCard)
                var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.height = 200
                params.width = 975
                params.bottomMargin = 30
                params.leftMargin = 50
                timeCard.layoutParams = params
                timeCard.setBackgroundResource(R.drawable.cardlayout)
                timeCard.foregroundGravity = Gravity.CENTER
                var nameLab = TextView(this)
                nameLab.text = parsed.name
                nameLab.setTypeface(null, Typeface.BOLD)
                nameLab.textSize = 20f
                nameLab.setTextColor(Color.WHITE)
                var txtNmPrms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                txtNmPrms.leftMargin = 50
                txtNmPrms.topMargin = 50
                nameLab.layoutParams = txtNmPrms
                timeCard.addView(nameLab)
                var descLav = TextView(this)
                descLav.text = parsed.description
                descLav.setTypeface(null, Typeface.ITALIC)
                descLav.textSize = 13f
                var txtDescPrms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                txtDescPrms.topMargin = 100
                txtDescPrms.leftMargin = 47
                descLav.layoutParams = txtDescPrms
                descLav.setTextColor(Color.WHITE)
                timeCard.addView(descLav)
            }
            else{
                print("invalid file")
            }

        }
}

        catch(e: Exception){
            println("error " + e)
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
        utcBar.valueFrom = -13f
        utcBar.valueTo = 13f
        utcBar.value = 0f
        val runnableCode = object: Runnable {
            override fun run() {
                val texTU = findViewById<TextView>(R.id.textView9)
                texTU.setText(utcBar.value.toString().replace("f", ""))
                handler.postDelayed(this, 1)
            }
        }



        plusBtn.setOnClickListener {
            darkenBg.visibility = View.VISIBLE
            darkenBg.animate().alpha(0.5f).setDuration(1000)
            addNewTimeZoneCard.visibility = View.VISIBLE
            addNewTimeZoneCard.animate().scaleY(-1f).alpha(1.0f)
            descEd.inputType = InputType.TYPE_CLASS_TEXT
            namEd.inputType = InputType.TYPE_CLASS_TEXT
            utcBar.isEnabled = true
        }
        addNewTimeZoneCard.rotationX = -180f
        addBtn.setOnClickListener{
            darkenBg.visibility = View.VISIBLE
            darkenBg.animate().alpha(0.5f).setDuration(1000)
            addNewTimeZoneCard.visibility = View.VISIBLE
            addNewTimeZoneCard.animate().scaleY(-1f).alpha(1.0f)
            descEd.inputType = InputType.TYPE_CLASS_TEXT
            namEd.inputType = InputType.TYPE_CLASS_TEXT
            utcBar.isEnabled = true
        }
        btnCl.setOnClickListener{
            darkenBg.animate().alpha(0.0f).setDuration(1000)
            addNewTimeZoneCard.animate().scaleY(1f).alpha(0.0f)
            if (addNewTimeZoneCard.alpha == 0.0f){
                addNewTimeZoneCard.visibility = View.GONE
                animationDone = true
            }
            descEd.inputType = InputType.TYPE_NULL
            descEd.setText("")
            namEd.setText("")
            utcBar.value = 0f
            namEd.inputType = InputType.TYPE_NULL
            utcBar.isEnabled = false
        }
        doneBtn.setOnClickListener {
            var timeCard = CardView(this)
            cardScr.addView(timeCard)
            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.height = 200
            params.width = 975
            params.bottomMargin = 30
            params.leftMargin = 50
            timeCard.layoutParams = params
            timeCard.setBackgroundResource(R.drawable.cardlayout)
            timeCard.foregroundGravity = Gravity.CENTER
            var nameLab = TextView(this)
            nameLab.text = namEd.text
            nameLab.setTypeface(null, Typeface.BOLD)
            nameLab.textSize = 20f
            nameLab.setTextColor(Color.WHITE)
            var txtNmPrms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            txtNmPrms.leftMargin = 50
            txtNmPrms.topMargin = 50
            nameLab.layoutParams = txtNmPrms
            timeCard.addView(nameLab)
            var descLav = TextView(this)
            descLav.text = descEd.text
            descLav.setTypeface(null, Typeface.ITALIC)
            descLav.textSize = 13f
            var txtDescPrms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            txtDescPrms.topMargin = 100
            txtDescPrms.leftMargin = 47
            descLav.layoutParams = txtDescPrms
            descLav.setTextColor(Color.WHITE)
            timeCard.addView(descLav)
            val gson = Gson()
            val crdJson = gson.toJson(CardJS(namEd.text.toString(), descEd.text.toString(), utcBar.value))
            val currentDateTime = LocalDateTime.now()
            File(filesDir.toString() + "/cardArray" + currentDateTime + ".json").writeText(crdJson)
            darkenBg.animate().alpha(0.0f).setDuration(1000)
            addNewTimeZoneCard.animate().scaleY(1f).alpha(0.0f)
            if (addNewTimeZoneCard.alpha == 0.0f){
                addNewTimeZoneCard.visibility = View.GONE
                animationDone = true
            }
            descEd.inputType = InputType.TYPE_NULL
            namEd.inputType = InputType.TYPE_NULL
            namEd.setText("")
            descEd.setText("")
            utcBar.value = 0f
            utcBar.isEnabled = false
        }
    }
}


data class CardJS(
    val name: String,
    val description: String,
    val utc: Float
)

