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
    //5 elevamos la variable number a un nivel superior (hoisting)
    var number by remember { mutableStateOf(0) }
    //6 instanciamos una nueva funcion composable que contendrá la lógica del lanzamiento de dado,
    //pasándole la variable de estado por parámetro.

    //8 esto nos trae de nuevo aquí, a introducir un segundo parámetro (lambda) cuyo funcionamiento
    //dicta que el valor del primer parámetro (number) será sustituido por lo que devuelva la
    //función lambda.
    LanzadorDados(number, {number = it})


}

//7 creamos la función con el contenido que ya conocemos. NOTA: los parámetros que reciben
//las funciones son INMUTABLES. Eso significa que deberemos pasarle una lambda que pueda
//modificar ese parámetro.

//9 esto nos obliga a declararlo (el segundo parámetro lambda) en la definición de la
//función LanzadorDados. A esta lambda la llamaremos changeNumber y, recordemos, es la
//encargada de modificar el valor contenido en number.
@Composable
fun LanzadorDados(number: Int, changeNumber : (Int)->Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(onClick = {
            //10 debido a que hemos añadido la lambda como parámetro, esta debe sustituir al
            //valor number previo, ya que lo que estamos haciendo es asignarle en el onClick
            //de button la lambda que hemos definido arriba, en los parámetros de la función
            //LanzadorDado; y en esa declaración conveníamos que la lambda changeNumber recibe
            //un Int, que es exactamente lo que recibe aquí, el nextInt de la librería Random.
            changeNumber(Random.nextInt(1, 7))
        }) {
            Text(text = "LANZAR DADO", fontSize = 40.sp)
        }

        Text(text = number.toString(), fontSize = 80.sp)
    }
}
