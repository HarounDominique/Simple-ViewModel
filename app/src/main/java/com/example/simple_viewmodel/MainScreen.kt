package com.example.simple_viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun MainScreen() {

    var number by remember { mutableStateOf(0) }
    //11 el siguente paso puede ser definir completamente el funcionamiento de esta lambda
    //14 y, finalmente, pegamos ese mismo contenido en la propia lambda en el argumento de la llamada a la función
    //LanzarDados; por lo que su funcionamiento queda totalmente definido aquí.
    LanzadorDados(number, {number = Random.nextInt(1, 7)})


}
//12 para ello se puede, en primer lugar, eliminar el Int de la lambda dentro del parámetro de la función LanzadorDados.
@Composable
fun LanzadorDados(number: Int, changeNumber : ()->Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(onClick = {
            //13 hecho eso, retiraremos el contenido que implicaba ese Int que acabamos de eliminar,
            // pero esta vez en la definición de la función, no de su llamada.
            changeNumber()
        }) {
            Text(text = "LANZAR DADO", fontSize = 40.sp)
        }

        Text(text = number.toString(), fontSize = 80.sp)
    }
}
