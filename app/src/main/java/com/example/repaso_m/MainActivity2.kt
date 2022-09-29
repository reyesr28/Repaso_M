package com.example.repaso_m

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity2 : AppCompatActivity() {

    //lateinit var appDB:AppDataBase
    lateinit var Lista:List<tablePost>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //var appDB:AppDataBase=AppDataBase.getDatabase(this)

        val appDB:AppDataBase by lazy { AppDataBase.getDatabase(this) }

        val recycler:RecyclerView=findViewById(R.id.recyclerRoom)
        recycler.layoutManager=LinearLayoutManager(applicationContext)


            GlobalScope.launch( Dispatchers.IO) {
                Lista= appDB.postDao().getAll()
                recycler.adapter=Adapter(Lista)
            }

    }


}