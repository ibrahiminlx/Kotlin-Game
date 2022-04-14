package com.example.oyun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var scoreList = ArrayList<skorlar>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val veritabani=this.openOrCreateDatabase("skorlar", MODE_PRIVATE,null)
        veritabani.execSQL("CREATE TABLE IF NOT EXISTS skorlar (foto INT,skor INT,hamle INT,zaman INT)")
        SkorRV.layoutManager=LinearLayoutManager(this)

        val cursor = veritabani.rawQuery("SELECT * FROM skorlar",null)
        val fotoIndex=cursor.getColumnIndex("foto")
        val skorIndex=cursor.getColumnIndex("skor")
        val hamleIndex=cursor.getColumnIndex("hamle")
        val zamanIndex=cursor.getColumnIndex("zaman")
        while (cursor.moveToNext()){
            scoreList.add(skorlar(cursor.getInt(fotoIndex),
                "Score:"+cursor.getInt(skorIndex),
                "Hamle:"+cursor.getInt(hamleIndex),
                "Zaman:"+cursor.getInt(zamanIndex)))
        }
        cursor.close()
        SkorRV.adapter = skorAdapter(scoreList)


        val button_kolay =findViewById<Button>(R.id.button_kolay)
        button_kolay.setOnClickListener {
            val intent = Intent(this,kolayoyun::class.java)
            startActivity(intent)
        }
        val button_zor =findViewById<Button>(R.id.button_zor)
        button_zor.setOnClickListener {
            val intent = Intent(this,zoroyun::class.java)
            startActivity(intent)
        }
    }

}