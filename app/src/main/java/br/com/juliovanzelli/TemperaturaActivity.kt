package br.com.juliovanzelli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_iluminacao.*
import kotlinx.android.synthetic.main.activity_temperatura.*
import android.content.Intent



class TemperaturaActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var temperaturaRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        database = FirebaseDatabase.getInstance()
        temperaturaRef = database.getReference("temperatura")

        temperaturaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Double::class.java)

                tvTemperatura.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
