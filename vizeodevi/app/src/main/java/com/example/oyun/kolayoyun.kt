package com.example.oyun

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.oyun.R.*
import com.example.oyun.R.drawable.*
import kotlinx.android.synthetic.main.activity_kolayoyun.*
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

private  const val TAG ="kolayoyun"
class kolayoyun : AppCompatActivity() {

    private lateinit var buttons:List<ImageButton>
    private lateinit var kartlar:List<OyunCard>
    var selectCard = ArrayList<Int>()
    var isLock=false
    var count=0//hamle sayısı
    var score=0//puan
    var match=0//eşleşen sayısı
    var time=0//timer -seconds
    var tisclickable=false
    var kolaybtnkaydet2: Button? =null
    var handlerkontrol=true




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_kolayoyun)
        kolaybtnkaydet2 = findViewById<Button>(id.kolaybutonkaydet)
        kolaybtnkaydet2!!.isEnabled=false


        val veritabani=this.openOrCreateDatabase("skorlar", MODE_PRIVATE,null)
        try {
            kolaybtnkaydet2!!.setOnClickListener {
                veritabani.execSQL("INSERT INTO skorlar (foto,skor,hamle,zaman) VALUES("+R.drawable.ic_oyun4+","+score+","+count+","+time+")")
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)


            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        val handler = Handler()
        handler.postDelayed(object: Runnable {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
                //time++
                val timer = findViewById<TextView>(id.kolayzaman)
                timer.text = LocalTime.MIN.plusSeconds(time.toLong()).toString()
                handler.postDelayed(this, 1000)
                if (handlerkontrol==true){
                    time++
                }
            }
        }, 1000)

        val button_anasayfa =findViewById<Button>(id.button_anasayfa)
        button_anasayfa.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val images = mutableListOf(
            ic_elektrik,
            ic_gunes,
            ic_kumsaati,
            ic_mektup,
            ic_yildiz,
            ic_aski,
            ic_geometri,
            ic_araba)
        images.addAll(images)
        images.shuffle()

        buttons= listOf(imagebutton1,imagebutton2,imagebutton3,imagebutton4,
            imagebutton5,imagebutton6,imagebutton7,imagebutton8,
            imagebutton9, imagebutton10,imagebutton11,imagebutton12,
            imagebutton13,imagebutton14,imagebutton15,imagebutton16)

        kartlar= buttons.indices.map { index -> OyunCard(images[index]) }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val kart=kartlar[index]

                if (!isLock){
                    Log.i(TAG, "button clicked")
                    oyunModels(index)

                }else{
                    //uyarı sesi
                }
            }
        }
    }

    private fun oyunModels(position:Int) {
        val kart=kartlar[position]
        val button = buttons[position]
        button.setImageResource(if (!kart.isFaceUp) kart.identifier else R.drawable.ic_soruisareti)
        if (selectCard.count()==0){
            selectCard.add(position)
            count++

        }else if(selectCard.count()==1 && selectCard[0]!=position){
            isLock=true
            selectCard.add(position)
            checkForMatch(selectCard[0],selectCard[1])
            selectCard.clear()
            count++

        }
        var scoreText: TextView = findViewById(R.id.scoreText)
        var hamleText: TextView = findViewById(R.id.hamleText)
        var eslesmeText: TextView = findViewById(R.id.eslesmeText)
        scoreText.text=" Skor: "+ score.toString()
        hamleText.text=" Hamle Sayısı: "+count.toString()
        eslesmeText.text=" Eslesme: "+(kartlar.count()/2).toString()+"/"+(match.toString())

    }

    private fun kartduzeltme() {
        Timer("SettingUp", false).schedule(1000) {
            kartlar.forEachIndexed { index, kart ->
                val button = buttons[index]
                if (!kart.isMatched) button.setImageResource(R.drawable.ic_soruisareti)

            }
            isLock=false
        }
    }

    private fun kartdurdurma() {
        Timer("SettingUp", false).schedule(100) {
            kartlar.forEachIndexed { index, kart ->
                val button = buttons[index]
                if (kart.isMatched) button.isEnabled=false

            }
            isLock=true
        }
    }
    private fun checkForMatch(position1: Int,position2: Int) {
        if (kartlar[position1].identifier==kartlar[position2].identifier){
            Toast.makeText(this,"Doğru Seçim",Toast.LENGTH_SHORT).show()
            kartlar[position1].isMatched=true
            kartlar[position2].isMatched=true
            score+=10
            match++


            if (kartlar.count() == kartlar.filter { kart -> kart.isMatched == true }.count()) {
                kolaybtnkaydet2!!.isEnabled=true
                handlerkontrol=false


            }

        }else{
            Toast.makeText(this,"Yanlış Seçim", Toast.LENGTH_SHORT).show()
            score-=2
        }
        kartduzeltme()
        kartdurdurma()

    }
}