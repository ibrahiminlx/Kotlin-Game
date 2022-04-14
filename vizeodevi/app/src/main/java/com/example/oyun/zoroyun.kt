package com.example.oyun

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_kolayoyun.*
import kotlinx.android.synthetic.main.activity_zoroyun.*
import java.lang.Exception
import java.time.LocalTime
import java.util.*
import kotlin.concurrent.schedule

private  const val TAG ="zoroyun"
class zoroyun : AppCompatActivity() {

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
        setContentView(R.layout.activity_zoroyun)
        kolaybtnkaydet2 = findViewById<Button>(R.id.zorbutonkaydet)
        kolaybtnkaydet2!!.isEnabled=false

        val veritabani=this.openOrCreateDatabase("skorlar", MODE_PRIVATE,null)
        try {
            kolaybtnkaydet2!!.setOnClickListener {
                veritabani.execSQL("INSERT INTO skorlar (foto,skor,hamle,zaman) VALUES("+R.drawable.ic_oyun6+","+score+","+count+","+time+")")
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)


            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        val handler = Handler()
        handler.postDelayed(object: Runnable {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
                //time++
                val timer = findViewById<TextView>(R.id.zorzaman)
                timer.text = LocalTime.MIN.plusSeconds(time.toLong()).toString()
                handler.postDelayed(this, 1000)
                if (handlerkontrol==true){
                    time++
                }
            }
        }, 1000)

        val button_anasayfa2 =findViewById<Button>(R.id.button_anasayfa2)
        button_anasayfa2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
          }

        val images = mutableListOf(
            R.drawable.ic_elektrik,
            R.drawable.ic_gunes,
            R.drawable.ic_kumsaati,
            R.drawable.ic_mektup,
            R.drawable.ic_yildiz,
            R.drawable.ic_aski,
            R.drawable.ic_geometri,
            R.drawable.ic_araba,
            R.drawable.ic_pc,
            R.drawable.ic_parantez,
            R.drawable.ic_saat,
            R.drawable.ic_insan,
            R.drawable.ic_eth,
            R.drawable.ic_ok,
            R.drawable.ic_atac,
            R.drawable.ic_dolar,
            R.drawable.ic_okey,
            R.drawable.ic_danger,

            )
        images.addAll(images)
        images.shuffle()

        buttons= listOf(zimageButton1,zimageButton2,zimageButton3,zimageButton4,zimageButton5,zimageButton6,
            zimageButton7,zimageButton8,zimageButton9,zimageButton10,zimageButton11,zimageButton12,
            zimageButton13,zimageButton14,zimageButton15,zimageButton16,zimageButton17,zimageButton18,
            zimageButton19,zimageButton20,zimageButton21,zimageButton22,zimageButton23,zimageButton24,
            zimageButton25,zimageButton26,zimageButton27,zimageButton28,zimageButton29,zimageButton30,
            zimageButton31,zimageButton32,zimageButton33,zimageButton34,zimageButton35,zimageButton36)

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
        var scoreText: TextView = findViewById(R.id.zscoreText)
        var hamleText: TextView = findViewById(R.id.zhamleText)
        var eslesmeText: TextView = findViewById(R.id.zeslesmeText)
        scoreText.text=" Skor: "+ score.toString()
        hamleText.text=" Hamle Sayısı: "+count.toString()
        eslesmeText.text=" Eslesme: "+(kartlar.count()/2).toString()+"/"+(match.toString())

    }





    private fun kartduzeltme() {
        Timer("SettingUp", false).schedule(1000) {
            kartlar.forEachIndexed { index, kart ->
                val button = buttons[index]
                //button.setImageResource(if (kart.isMatched) kart.identifier else R.drawable.ic_soruisareti)
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

