package ru.pgk63.focusstart.ui.screens.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.onEach
import ru.pgk63.focusstart.common.extension.launchWhenStarted
import ru.pgk63.focusstart.common.extension.openBrowser
import ru.pgk63.focusstart.common.extension.openMap
import ru.pgk63.focusstart.common.extension.openPhone
import ru.pgk63.focusstart.data.database.user.model.RequestBinHistory
import ru.pgk63.focusstart.data.network.bin.model.BinDetails

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
){
    val context = LocalContext.current

    var bin by rememberSaveable { mutableStateOf("") }

    var binDetails by remember { mutableStateOf<BinDetails?>(null) }
    var requestBinHistory by remember { mutableStateOf(listOf<RequestBinHistory>()) }

    viewModel.responseBinDetails.onEach { result ->
        binDetails = result
    }.launchWhenStarted()

    viewModel.requestBinHistory.onEach { result ->
        requestBinHistory = result
    }.launchWhenStarted()

    Scaffold(
        topBar = {
            OutlinedTextField(
                value = bin,
                onValueChange = { bin = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(onGo = {
                    viewModel.getBinDetails(bin)
                })
            )
        }
    ) { paddingValues ->
        LazyColumn {

            item {
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow {
                    items(requestBinHistory) { item ->
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    bin = item.name
                                    viewModel.getBinDetails(item.name)
                                },
                            shape = AbsoluteRoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = item.name,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                binDetails?.let {
                    Card(
                        modifier = Modifier.padding(10.dp),
                        shape = AbsoluteRoundedCornerShape(20.dp)
                    ) {
                        Column {

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Scheme: ${binDetails!!.scheme}",
                                modifier = Modifier.padding(2.dp)
                            )
                            Text(
                                text = "Type: ${binDetails!!.type}",
                                modifier = Modifier.padding(2.dp)
                            )
                            Text(
                                text = "Brand: ${binDetails!!.brand}",
                                modifier = Modifier.padding(2.dp)
                            )

                            Divider()

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Country",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W900
                            )

                            TextButton(
                                onClick = {
                                    if(binDetails!!.country?.latitude != null
                                        && binDetails!!.country?.longitude != null){
                                        context.openMap(
                                            latitude = binDetails!!.country?.latitude!!,
                                            longitude = binDetails!!.country?.longitude!!
                                        )
                                    }
                                }
                            ) {
                                Text(
                                    text = "${binDetails!!.country?.name} ${binDetails!!.country?.emoji}",
                                    modifier = Modifier.padding(2.dp)
                                )
                            }

                            Divider()

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Bank",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W900
                            )

                            Text(
                                text = binDetails!!.bank?.name.toString(),
                                modifier = Modifier.padding(2.dp)
                            )

                            binDetails!!.bank?.phone?.let { phone ->
                                TextButton(onClick = { context.openPhone(phone) }) {
                                    Text(
                                        text = "Phone: $phone ->",
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }

                            binDetails!!.bank?.url?.let { url ->
                                TextButton(onClick = { context.openBrowser(url) }) {
                                    Text(
                                        text = "Url: $url ->",
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
            }
        }
    }
}