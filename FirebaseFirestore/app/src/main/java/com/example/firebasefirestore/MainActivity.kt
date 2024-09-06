package com.example.firebasefirestore

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import com.example.firebasefirestore.ui.theme.FirebaseFirestoreTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Ensure Firebase is initialized
        setContent {
            App(db)
        }
    }
}

@Composable
fun App(db: FirebaseFirestore) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    FirebaseFirestoreTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    Arrangement.Center
                ) {
                    Text(
                        text = "Um App Firebase Firestore",
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Row {
                    Text(
                        text = "Nome:",
                        modifier = Modifier.width(100.dp),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    TextField(value = nome, onValueChange = { nome = it })
                }

                Row {
                    Text(
                        text = "Telefone:",
                        modifier = Modifier.width(100.dp),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    TextField(value = telefone, onValueChange = { telefone = it })
                }

                Button(onClick = {
                    val pessoas = hashMapOf(
                        "nome" to nome,
                        "telefone" to telefone
                    )

                    db.collection("Clientes").add(pessoas)
                        .addOnSuccessListener {
                            Log.d("Firestore", "Document successfully written!")
                        }
                        .addOnFailureListener { e ->
                            Log.w("Firestore", "Error writing document", e)
                        }
                }) {
                    Text(text = "Cadastrar")
                }

                ReadInterface(db)
            }
        }
    }
}

@Composable
fun ReadInterface(db: FirebaseFirestore) {
    var documents by remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }

    LaunchedEffect(Unit) {
        db.collection("Clientes")
            .get()
            .addOnSuccessListener { result ->
                documents = result.map { doc ->
                    mapOf(
                        "nome" to (doc.data["nome"] as? String ?: ""),
                        "telefone" to (doc.data["telefone"] as? String ?: "")
                    )
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents: ", e)
            }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        if (documents.isEmpty()) {
            Text("Nenhum dado disponível", fontSize = 16.sp)
        } else {
            LazyColumn {
                items(documents) { document ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Nome: ${document["nome"]}", fontSize = 16.sp)
                        Text("Telefone: ${document["telefone"]}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                    }
                }
            }
        }
    }
}
