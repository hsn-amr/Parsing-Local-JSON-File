package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.File
import java.lang.Exception
import java.nio.charset.Charset
import java.nio.file.Files.size




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = loadUrlsFromJson(loadJSONFromAsset())

        val rv =  findViewById<RecyclerView>(R.id.rv)
        val adapter = RV(images, this)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    private fun loadJSONFromAsset(): String {
        var json = ""
        try {
            val data = assets.open("data.json")
            val size = data.available()
            val buffer = ByteArray(size)
            data.read(buffer)
            data.close()
            json = String(buffer, Charsets.UTF_8)
        }catch (e: Exception){
            Log.e("TAG", "$e")
        }
        return json
    }

    fun loadUrlsFromJson(json: String): ArrayList<String>{

        val images = ArrayList<String>()

        val array = JSONArray(json)
        val len = array.length()
        for (item in 0 until len-1){
            val image = array.getJSONObject(item)
            val url = image.getString("url")
            images.add(url)
        }

        return images
    }
}