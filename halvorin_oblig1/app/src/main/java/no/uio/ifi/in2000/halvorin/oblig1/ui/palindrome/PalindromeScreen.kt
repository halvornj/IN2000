package no.uio.ifi.in2000.halvorin.oblig1.ui.palindrome

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import no.uio.ifi.in2000.halvorin.oblig1.isPalindrome
import no.uio.ifi.in2000.halvorin.oblig1.ui.theme.Halvorin_oblig1Theme


@Composable
fun PalindromeScreen(navController: NavController){
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.SpaceBetween) {
    PalindromeChecker()

    Button(onClick = { navController.navigate("unitconverter")}, Modifier.fillMaxWidth()) {
        Text(text = "unitconverter-side")
    }
    }
}




@Suppress("SpellCheckingInspection")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PalindromeChecker(modifier: Modifier = Modifier){
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