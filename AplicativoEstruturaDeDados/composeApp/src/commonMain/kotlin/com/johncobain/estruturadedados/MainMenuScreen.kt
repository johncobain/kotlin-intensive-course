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
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import aplicativoestruturadedados.composeapp.generated.resources.Res
import kotlin.math.sin


@Composable
fun MainMenuScreen() {
    var items by remember { mutableStateOf(listOf<Pair<String, Int>>()) }
    var searchQuery by remember { mutableStateOf("") }
    var newItemName by remember { mutableStateOf("") }
    var newIteQuantity by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var editIndex by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar") },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            singleLine = true
        )
        Button(onClick = { showDialog = true }, modifier = Modifier.align(Alignment.End).padding(16.dp)) {
            Text("Adicionar item", fontSize = 23.sp)
        }
        if(showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    editIndex = -1
                },
                title = {
                    Text(if(editIndex>=0) "Editar item" else "Adicionar item", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                },
                text = {
                    Column {
                        TextField(
                            value = newItemName,
                            onValueChange = { newItemName = it },
                            label = { Text("Nome do item") },
                            //accept utf-8
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = newIteQuantity,
                            onValueChange = { newIteQuantity = it },
                            label = { Text("Quantidade") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true

                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if(newItemName.isNotBlank()){
                            if(editIndex>=0){
                                items = items.toMutableList().also{
                                    it[editIndex] = newItemName to (newIteQuantity.toIntOrNull()?:1)
                                }
                            } else{
                                items = items + (newItemName to (newIteQuantity.toIntOrNull()?:1))
                            }
                            newItemName = ""
                            newIteQuantity = ""
                            showDialog = false
                            editIndex = -1
                        }
                    }){
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }){
                        Text("Cancelar")
                    }
                }
            )
        }
        val filteredItems = items.filter { it.first.contains(searchQuery, ignoreCase = true)}
        if(filteredItems.isEmpty()){
            Text("Não foram encontrados resultados.", modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp))
        }else{
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)){
                    Text("Nome do Item", Modifier.weight(3f))
                    Text("Quantidade", Modifier.weight(7f))
                }
                filteredItems.forEachIndexed{ index, (name, quantity) ->
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween){
                        Text(name, Modifier.weight(6f))
                        Text(quantity.toString(), Modifier.weight(2f))
                        Row{
                            Button(
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
                                onClick = {
                                    editIndex = index
                                    newItemName = name
                                    newIteQuantity = quantity.toString()
                                    showDialog = true
                                }
                            ){ Text("Editar") }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.error),
                                onClick = {
                                    items = items.filterIndexed{ i, _ -> i!= index }
                                    if(editIndex == index) {
                                        editIndex = -1
                                    }
                                }
                            ){ Text("Apagar") }
                        }
                    }
                }
            }
        }
    }
}