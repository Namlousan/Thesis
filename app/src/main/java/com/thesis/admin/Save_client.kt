package com.thesis.admin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode

class Save_client : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var tv :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_client)
       //fetch captcha data

            var intent =intent
        val capt = intent.getStringExtra("result")
        val result_tv =findViewById<TextView>(R.id.fetched_data)
        result_tv.text= capt
        ivQRCode = findViewById(R.id.qr)
        //qr code generate

        val  data =result_tv.text.toString()
        if (data.isEmpty()){
            Toast.makeText(this, "enter some data",Toast.LENGTH_SHORT).show()
        }else{
            val writer = QRCodeWriter()
            try{
                val bitMatrix =writer.encode(data, BarcodeFormat.QR_CODE, 512,512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp =Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                for (x in 0 until  width){
                    for (y in 0 until height){
                        bmp.setPixel(x,y, if(bitMatrix[x,y])Color.BLACK else Color.WHITE)
                    }
                }
                ivQRCode.setImageBitmap(bmp)

        }catch (e: WriterException){}

        val button = findViewById<Button>(R.id.QRback)
        button.setOnClickListener{
            val intent = Intent(this, Client::class.java)
            startActivity(intent)
        }
    }
}}