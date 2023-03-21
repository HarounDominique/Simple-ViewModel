package com.example.simple_viewmodel

import android.widget.Button
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
    //2 una vez en su propio fichero, creamos el núcleo visual en la MainScreen
    //(en este caso una columna que ocupa toda la pantalla, con un padding general,
    //que contiene un botón que se encargará de lanzar el dado y un texto que
    //refleje el número del dado).
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //4 aquí implementamos el estado, ya que, recordemos, la vista no se recompondrá
        //salvo que haya un cambio de estado (no todas las variables son de estado);
        //para lograrlo, declaramos la variable 'number' y la inicializamos con delegación
        //(by) con remember de mutablestateof y un valor inicial. La delegación y el by
        //hace que number adquiera el tipo de su contenido, en este caso un int, en lugar
        //de su 'tipo propio' que es el mutableState. En caso de no usar delegación, se
        //deberá poner .value al final de la variable para lograr esto mismo.
        var number by remember{ mutableStateOf(0) }
                Button(onClick = {
            //3 dispuesta la parte visual, procedemos a trabajar en la lógica dentro
            //del botón, en este caso. Lo que nos interesa es que este genere un
            //número de entre 1 y 6.
            number = (Random.nextInt(1,7))
        }) {
            Text(text = "LANZAR DADO", fontSize = 40.sp)
        }

        Text(text = number.toString(), fontSize = 80.sp)
    }
}