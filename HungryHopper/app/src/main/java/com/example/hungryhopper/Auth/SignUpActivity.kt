package com.example.hungryhopper.Auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hungryhopper.ChooseLocationActivity
import com.example.hungryhopper.MainActivity
import com.example.hungryhopper.Model.UserModel
import com.example.hungryhopper.R
import com.example.hungryhopper.Utils.USER_NODE
import com.example.hungryhopper.databinding.ActivitySignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        auth = Firebase.auth

        database = Firebase.database.reference

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        val toLogIn = binding.alreadyHaveAcc

        toLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUpbtn.setOnClickListener {

            email = binding.email.text.toString().trim()
            username = binding.userName.toString()
            password = binding.password.toString().trim()

            if (email.isBlank() || password.isBlank() || username.isBlank()) {
                Toast.makeText(this, "Please Fill all the Details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }

        binding.googleSignIn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    val credentials = GoogleAuthProvider.getCredential(account?.idToken, null)
                    auth.signInWithCredential(credentials).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "SignIn Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show()
            }
        }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account created Successfully", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, ChooseLocationActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "Create Account: Failure", task.exception)
            }
        }
    }

    private fun saveUserData() {
        email = binding.email.text.toString().trim()
        username = binding.userName.toString()
        password = binding.password.toString().trim()

        val user = UserModel(username, email, password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child(USER_NODE).child(userId).setValue(user)
    }
}