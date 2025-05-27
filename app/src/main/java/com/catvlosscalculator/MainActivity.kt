
package com.catvlosscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.catvlosscalculator.ui.theme.CATVLossCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CATVLossCalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LossCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun LossCalculatorScreen() {
    var cableType by remember { mutableStateOf("RG-6") }
    var lengthFeet by remember { mutableStateOf("100") }
    var splitterType by remember { mutableStateOf("2-way") }
    var splitterCount by remember { mutableStateOf("1") }
    var totalLoss by remember { mutableStateOf(0.0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Cable Type:")
        TextField(value = cableType, onValueChange = { cableType = it })
        Spacer(modifier = Modifier.height(8.dp))

        Text("Cable Length (ft):")
        TextField(value = lengthFeet, onValueChange = { lengthFeet = it })
        Spacer(modifier = Modifier.height(8.dp))

        Text("Splitter Type:")
        TextField(value = splitterType, onValueChange = { splitterType = it })
        Spacer(modifier = Modifier.height(8.dp))

        Text("Splitter Count:")
        TextField(value = splitterCount, onValueChange = { splitterCount = it })
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            totalLoss = LossCalculator.calculateTotalLoss(
                cableType,
                lengthFeet.toDoubleOrNull() ?: 0.0,
                splitterType,
                splitterCount.toIntOrNull() ?: 0
            )
        }) {
            Text("Calculate Total Loss")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Total Loss: $totalLoss dB")
    }
}
