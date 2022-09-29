package com.example.repaso_m

import Beans.Post
import Interface.PlaceHolderApi
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var service:PlaceHolderApi
    lateinit var postsList:List<Post>
    lateinit var pos:Post
    lateinit var appDB:AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDB=AppDataBase.getDatabase(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service=retrofit.create<PlaceHolderApi>(PlaceHolderApi::class.java)

        val btnConsultar:Button=findViewById(R.id.btnConsultar)

        val txtId:EditText=findViewById(R.id.txtId)
        btnConsultar.setOnClickListener(){
            getPostId(txtId.text.toString().toInt())
        }

        val btnGrabar:Button=findViewById(R.id.btnGrabar)


        btnGrabar.setOnClickListener(){

            val regPost=tablePost(pos.id,pos.userId,pos.title,pos.body)

            GlobalScope.launch( Dispatchers.IO) {
                appDB.postDao().insert(regPost)
            }

            val txtRes:TextView=findViewById(R.id.txtRes)
            txtRes.text="Se registro con Exito"

        }

        val btnListar:Button=findViewById(R.id.btnListar)


        btnListar.setOnClickListener(){

            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }


        }

    fun getPost(){

        service.getListado().enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                postsList=response?.body()!!

                val txtRes:TextView=findViewById(R.id.txtRes)

                if(postsList!=null){

                    for(item in postsList){
                        var texto:String=""
                        texto+="Id: ${item.id} \n"
                        texto+="UserId: ${item.userId} \n"
                        texto+="Title: ${item.title} \n"
                        texto+="Body: ${item.body} \n\n"
                        txtRes.append(texto)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t?.printStackTrace()
            }

        })

    }

    fun getPostId(id:Int){

        service.getPostId(id).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                pos=response?.body()!!

                val txtRes:TextView=findViewById(R.id.txtRes)

                txtRes.text="Id: ${pos.id} \n"+
                        "UserId: ${pos.userId} \n" +
                        "Title: ${pos.title}\n" +
                        "Body: ${pos.body}"
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                t?.printStackTrace()
            }
        })
    }

}