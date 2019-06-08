package br.com.juliovanzelli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance();

        if (usuarioLogado()) {
            proximaTelaLogada()
        }

        botaoEntrar.setOnClickListener {
            validarUsuario()
        }
    }

    private fun validarUsuario() {
        val email = inputEmail.editText?.text.toString()
        val senha = inputSenha.editText?.text.toString()

        inputEmail.isErrorEnabled = false

        if( email.isEmpty() ) {
            inputEmail.isErrorEnabled = true
            inputEmail.error = "Favor preencher o e-mail"
            return
        }

        inputSenha.isErrorEnabled = false

        if( senha.isEmpty() ) {
            inputSenha.isErrorEnabled = true
            inputSenha.error = "Favor preencher a senha"
            return
        }

        mAuth.signInWithEmailAndPassword(
            email,
            senha
        ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    proximaTelaLogada()
                } else {
                    Toast.makeText(
                        this,
                        "Falha na autenticação",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
    }

    private fun usuarioLogado(): Boolean {
        return mAuth.currentUser != null
    }

    private fun proximaTelaLogada() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
