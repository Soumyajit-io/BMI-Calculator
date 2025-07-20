package com.soumyajit.bmi.com.soumyajit.bmi

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainFunction() {
    var height by remember { mutableStateOf("") }
    var Weight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var resultBox by remember {mutableStateOf(false)}

    var context = LocalContext.current
    fun bmi(){
        var h = height.toDoubleOrNull()
        var w = Weight.toDoubleOrNull()
        if( h == null || w ==null){
            Toast.makeText(context,"Invalid Value", Toast.LENGTH_LONG).show()
            return
        }
        else{
            val heightInMeters = h / 100
            val r = w / (heightInMeters * heightInMeters)
            result = r.toString()
        }
    }
    Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Heading
            Text(
                text = "BMI Calculator",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Spacer(modifier = Modifier.height(110.dp))


            // Input Fields
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height (cm)") },
                    modifier = Modifier
                        .weight(1f)
                )
                OutlinedTextField(
                    value = Weight,
                    onValueChange = { Weight = it },
                    label = { Text("Weight (kg)") },
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            //calculation
            Button(onClick = {resultBox= true
                bmi()}) {
                Text("Calculate")
            }
            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = "Made with ❤️ by SoumyajiT",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        }
        if(resultBox){
            AlertDialog(onDismissRequest = {resultBox=false},
                confirmButton = {
                    TextButton(onClick = { resultBox = false }) {
                        Text("OK")

                }
                                },
                title = {Text("BMI Result :")},
                    text = {
                        Column {
                            Text("Your BMI is $result", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))

                            Text("BMI Chart:", fontWeight = FontWeight.Bold)
                            Text("• Underweight: < 18.5")
                            Text("• Normal: 18.5 – 24.9")
                            Text("• Overweight: 25.0 – 29.9")
                            Text("• Obese: 30+")
                        }
            })

        }

    }
}


@Preview
@Composable
fun MainFunctionPreview(){
    MainFunction()
}