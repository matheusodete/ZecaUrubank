package com.example.zecaurubank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Empty;

import java.util.HashMap;
import java.util.Map;

public class cadastro extends AppCompatActivity {

    private TextView text_tela_user;
    private EditText nome_cadastro,cpf_cadastro,email,senha1,senha2,endereco,idade;
    private Button bt_cadastro;
    private RadioButton masculino, feminino;
    String[] mensagens = {"Preencha todos os campos","Cadastro realizado com sucesso!"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        getSupportActionBar().hide();
        IniciarComponentes();


        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = nome_cadastro.getText().toString();
                String email_2 = email.getText().toString();
                String senha = senha1.getText().toString();
                String senha_2 = senha2.getText().toString();
                String cpf = cpf_cadastro.getText().toString();





                if (nome.isEmpty() || email_2.isEmpty() || senha.isEmpty() || senha_2.isEmpty() || cpf.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else{
                    CadastrarUsuario(view);
                }
            }
        });

        text_tela_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cadastro.this,login.class);
                startActivity(intent);
            }
        });
    }




    private void CadastrarUsuario(View v){
        String email_2 = email.getText().toString();
        String senha = senha1.getText().toString();
        String senha_2 = senha2.getText().toString();
        String cpf = cpf_cadastro.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email_2,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    SalvarDadosUsuario();

                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.GREEN);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com 6 caracteres.";

                    }catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esse e-mail já está em uso";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido";
                    }catch (Exception e){
                        erro = "Erro ao cadastrar usuário";
                    }

                    Snackbar snackbar = Snackbar.make(v,erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                }

            }
        });

    }

    private void SalvarDadosUsuario() {
        String nome = nome_cadastro.getText().toString();
        String idade_c = idade.getText().toString();
        String endereco_c = endereco.getText().toString();
        String resultSexo = null;
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (feminino.isChecked()) {
            resultSexo = "feminino";
        } else if (masculino.isChecked()) {
            resultSexo = "masculino";
        } else {

        }
        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        usuarios.put("idade_c", idade_c);
        usuarios.put("endereco_c", endereco_c);
        usuarios.put("sexo", resultSexo);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("db", "Sucesso ao salvar os dados");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                    }
                });


    }


    private void IniciarComponentes() {
        text_tela_user=findViewById(R.id.botao_voltar);
        nome_cadastro = findViewById(R.id.nome_cadastro);
        cpf_cadastro = findViewById(R.id.cpf_cadastro);
        email = findViewById(R.id.email);
        senha1 = findViewById(R.id.senha1);
        senha2 = findViewById(R.id.senha2);
        endereco = findViewById(R.id.endereco);
        idade = findViewById(R.id.idade);
        bt_cadastro = findViewById(R.id.bt_cadastro);
        feminino = findViewById(R.id.radio_fem);
        masculino=findViewById(R.id.radio_male);

    }



}