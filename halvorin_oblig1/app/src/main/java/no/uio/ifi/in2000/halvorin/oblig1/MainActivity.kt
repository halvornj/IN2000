package no.uio.ifi.in2000.halvorin.oblig1

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.coreshims.SoftwareKeyboardControllerCompat
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import no.uio.ifi.in2000.halvorin.oblig1.ui.theme.Halvorin_oblig1Theme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Halvorin_oblig1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}

@Suppress("SpellCheckingInspection")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PalindromeChecker(modifier:Modifier = Modifier){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
        var text by remember { mutableStateOf("") }
        var answer by remember { mutableStateOf("...") }

        val keyboardController = LocalSoftwareKeyboardController.current
        fun submit(s:String){
            keyboardController?.hide()
            Log.d("DEBUG", s)
            answer = "teksten er${if(!isPalindrome(s)){" ikke"}else ""} et palindrom!" //<- litt goofy
        }

        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Skriv inn") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { submit(text) })
            )
            Button(onClick = {submit(text) }) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "")
            }
        }
        //TODO this could be some sort of a neat popup
        Text(text = answer)
    }
}

@Preview()
@Composable
fun PalindromeCheckerPreview() {
    Halvorin_oblig1Theme {
        PalindromeChecker()
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UnitConverter(){
    val keyboardController = LocalSoftwareKeyboardController.current


    var expanded by remember { mutableStateOf(false) }
    val options = ConverterUnits.entries
    var selected by remember { mutableStateOf(options[0]) }
    var num by remember { mutableIntStateOf(0) }
    Column {


        Row {
            TextField(
                value = num.toString(),
                onValueChange = { if (it.isDigitsOnly()) num = it.toInt() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
            )
            //todo keyboard sometimes pops up here? why
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selected.toString(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },

                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    options.forEach { opt ->
                        DropdownMenuItem(text = { Text(opt.toString()) }, onClick = {
                            selected = opt
                            expanded = false
                        })
                    }
                }
            }
        }
        Text(text = "tilsvarer ${converter(num, selected).toString()} liter.")
    }
}

@Preview(showSystemUi = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}