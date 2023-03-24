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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random


//15 para crear nuestro viewmodel, lo primero es crear la clase que lo contendrá, heredando de ViewModel
class DadoViewModel : ViewModel(){
    private var _number by mutableStateOf(0)

    //16 aquí el primer paso será definir las propiedades de nuestro viewmodel, que representarán aquellos
    //elementos que cambian en la vista (equivalen a los antiguos estados mutables)
    //18 teniendo en cuenta que ahora empleamos ViewModel y este pertenece al paquete LifeCycle, ya no será
    //necesario el remember. No mantenemos la delegación del 'by'; ya que es sustituída por un '=' para
    //poder generar (con clic derecho) la backingProperty (?) sin el setter, que no será necesario en este caso.
    //NOTA: aquí hay magia de por medio. Quedaría tal que así:
    //NOTA2: también será necesario recuperar la delegación mediante 'by' (sustituyendo al '=' que ya no nos hace falta
    // después de haber generado el código mágico necesario) ya que, como acabamos de ver, no contábamos con delegación,
    //y esta es necesaria para garantizar que number devuelva Int y no mutableState.
        val number get() = _number

    //23 creamos una función changeNumber que reciba un Int newNumber que será el nuevo valor de la variable de estado _number.
    fun changeNumber(){
        //24 tengase en cuenta que se emplea _number dado que es el atributo privado y al cual SÓLO se accederá desde aquí

        //27 la lógica pasa a sustituir al argumento newNumber; por lo que el argumento ya no es necesario y se elimina.
        _number = Random.nextInt(1, 7)
    }
}


//19 faltaría instanciar nuestro DadoViewModel para poder usarlo dentro de la MainScreen,
// o bien pasárselo por parámetro como argumento.
@Composable
fun MainScreen() {
    //17 en este caso, el estado es esta variable de estado tipo Int 'number', que movemos dentro de la clase DadoViewModel

    //20 podemos, como hemos dicho, instanciar un objeto llamado, por ejemplo, miviewmodel, de tipo DadoViewModel.
    //NOTA: antes de poder importar viewModel() deberemos pegar en el gradle del módulo la dependencia necesaria para usar viewModel.
    //lo he copiado del proyecto shopping-list de Alejandro.
    val miViewModel : DadoViewModel = viewModel()

    //21 el siguiente paso es sustituir el number como argumento de la función LanzadorDados por miViewModel.number, que es la
    //pripiedad de nuestro viewModel que ahora se encarga de mantener las variables de estado, como hemos mencionado en el punto 16
    //22 no obstante, seguimos teniendo un problema en lo que respecta al segundo argumento (la lambda); deberemos pasar esta lógica
    //al interior de la clase DadoViewModel.

    //25 deberemos, ahora sí, sustituir la lambda del segundo argumento por la llamada a la función changeNumber() de miViewModel.
    //NOTA: téngase en cuenta que se mantienen las llaves ({}) dado que debe seguir siendo una lambda ya que así lo exige el
    //stateHoisting de antes (LanzadorDados() requiere por su definición una lambda como segundo argumento).

    //26 para finalizar y por coherencia, moeveremos la lógica de Random.nextInt dentro de la función changeNumber() dentro de la clase
    //miViewModel
    LanzadorDados(miViewModel.number, {miViewModel.changeNumber()})


}

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

            changeNumber()
        }) {
            Text(text = "LANZAR DADO", fontSize = 40.sp)
        }

        Text(text = number.toString(), fontSize = 80.sp)
    }
}
