package com.example.qrcodegenerator

import android.graphics.Bitmap
import android.graphics.Color
import android.opengl.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode

class MainActivity : AppCompatActivity() {

    private lateinit var ivQRCode: ImageView
    private lateinit var etDate: EditText
    private lateinit var btnGeneratorQRCode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ivQRCode = findViewById(R.id.ivQRCode)
        etDate = findViewById(R.id.etData)
        btnGeneratorQRCode = findViewById(R.id.btnGeneratorQRCode)

        btnGeneratorQRCode.setOnClickListener {
            val data = etDate.text.toString().trim()

            if (data.isEmpty()) {
                Toast.makeText(this, "enter some data", Toast.LENGTH_SHORT).show()
            } else {
                val writer = QRCodeWriter()
                try {

                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for (x in 0 until width) {
                        for (y in 0 until height) {
                            bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    ivQRCode.setImageBitmap(bmp)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }

            }
        }

    }
}