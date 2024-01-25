package no.uio.ifi.in2000.halvorin.oblig1.ui.unitconverter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import no.uio.ifi.in2000.halvorin.oblig1.ConverterUnits
import no.uio.ifi.in2000.halvorin.oblig1.converter

@Composable
fun UnitConverterScreen(navController: NavController){
    Column (horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.SpaceBetween){
        UnitConverter()
        Button(onClick = { navController.navigate("palindrome") }, Modifier.fillMaxWidth()) {
            Text(text = "palindrom-side")
        }
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