package com.sirius.test_app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dotaLogo : ImageView = findViewById(R.id.logoImageView)
        val scrollView : ScrollView = findViewById(R.id.scrollView)
        scrollView.setOnScrollChangeListener { view, x, y, oldx, oldy ->  dotaLogo.scrollTo(x,y)  }


        val installButton = findViewById<Button>(R.id.installButton)
        installButton.text.asIterable().forEach(){
            run {
                if (it != 'I')
                    it.uppercaseChar()
            }
        }
    }

    //Если бы хотел грузить картинки через DataModel, то сделал бы это так:
    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            Log.e("src", src)
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            val myBitmap = BitmapFactory.decodeStream(input)
            Log.e("Bitmap", "returned")
            myBitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}