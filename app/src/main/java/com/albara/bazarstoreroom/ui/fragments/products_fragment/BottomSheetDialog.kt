package com.albara.bazarstoreroom.ui.fragments.products_fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.ui.ui.theme.BazarStoreroomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(
    product: Product,
    sheetIsVisible : Boolean,
    onDismiss : (Product) -> Unit

) {
    BazarStoreroomTheme {
        var textFieldText by remember { mutableStateOf("") }
        val sheetState = rememberModalBottomSheetState()

        if (sheetIsVisible) {
            ModalBottomSheet(
                modifier = Modifier
                    .fillMaxWidth(),
                onDismissRequest = {
                    textFieldText = ""
                    onDismiss(product) },
                sheetState = sheetState,
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .padding(horizontal = 5.dp),
                        value = textFieldText,
                        onValueChange = { textFieldText = it },
                        placeholder = { textFieldText = "Amount" }
                    )
                }
            }
        }
    }

}