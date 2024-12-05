package com.johncobain.estruturadedados

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    //base de dados
    val users = mapOf("johncobain" to "199407", "ronaldo" to "fenomeno")

    // armazenar as entradas do usuario
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // controle para mensagens de alerta
    var showDialog by remember { mutableStateOf(false) }
    var alertText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Header()
            Spacer(modifier = Modifier.height(16.dp))

            val usernameError = UsernameField(username) { username = it }
            Spacer(modifier = Modifier.height(16.dp))

            val passwordError = PasswordField(password) { password = it }
            Spacer(modifier = Modifier.height(16.dp))

            LoginButton(username, password, users, usernameError, passwordError, onLogin) {
                showDialog = true; alertText = it
            }

            if(showDialog){
                ShowDialog({showDialog = false}, alertText)
            }
        }
    }
}

@Composable
fun Header() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = "Curso KMP", fontSize = 30.sp)
    }
}

@Composable
fun UsernameField(username: String, onValueChange: (String)->Unit): String {
    var usernameError by remember { mutableStateOf("") }

    TextField(
        value = username,
        onValueChange = {
            onValueChange(it)
            usernameError = if(it.length<3) "O nome de usuário deve ter pelo menos 3 caracteres" else ""
        },
        label = { Text("Usuário") },
        modifier = Modifier.fillMaxWidth(0.8f),
        singleLine = true,
        isError = usernameError.isNotEmpty()
    )
    Text(text = usernameError, color = Color.Red)

    return usernameError
}

@Composable
fun PasswordField(password: String, onValueChange: (String)->Unit): String {
    var passwordError by remember { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = {
            onValueChange(it)
            passwordError = if(it.length<6) "A senha deve ter pelo menos 6 caracteres" else ""
        },
        label = { Text("Senha") },
        modifier = Modifier.fillMaxWidth(0.8f),
        singleLine = true,
        isError = passwordError.isNotEmpty()
    )
    Text(text = passwordError, color = Color.Red)

    return passwordError
}

@Composable
fun LoginButton(username: String, password: String, users: Map<String, String>, usernameError: String, passwordError: String, onLogin: () -> Unit, showDialog: (String)->Unit) {
    Button(onClick = {
        when {
            username.isEmpty() || password.isEmpty() -> showDialog("Você não inseriu as informações devidas.")
            usernameError.isNotEmpty() || passwordError.isNotEmpty() -> showDialog("Por favor, corrija os erros nos campos.")
            users[username] != password -> showDialog("As credenciais fornecidas estão incorretas.")
            else ->{
                onLogin()
            }
        }
    }){
        Text("Entrar")
    }
}

@Composable
fun ShowDialog(onDismiss: ()->Unit, message: String) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss){ Text("OK") }
        },
        text = { Text(message) }
    )
}