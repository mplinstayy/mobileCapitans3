package com.example.capitanspt3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.capitanspt3.recycler.UserRepository
import com.example.capitanspt3.recycler.uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var entryButton: Button
    private lateinit var emailET:EditText
    private lateinit var password:EditText

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entryButton = findViewById(R.id.entry_button)
        emailET = findViewById(R.id.editTextEmailAddress)
        password = findViewById(R.id.editTextPassword)

        entryButton.setOnClickListener {

            val email = emailET.text.toString()
            val pass = password.text.toString()

            firebaseAuth = FirebaseAuth.getInstance()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, CurrentTaskActivity::class.java)
                        intent.putExtra("uri", firebaseAuth.uid.toString())
                        startActivity(intent)

                        uri =  firebaseAuth.uid.toString()


                        Log.d("LOGIN", firebaseAuth.uid.toString())
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }


    }


}