package mx.edu.delasalle.mvvmlogin.app.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.edu.delasalle.mvvmlogin.app.states.ComputerDetailState

@Composable
fun ComputerDetailScreen(
    state: ComputerDetailState,
    addNewComputer: (String, String) -> Unit,
    updateComputer: (String, String) -> Unit

) {

    var title by remember(state.computer?.title){ mutableStateOf(state.computer?.title ?: "")}
    var description by remember(state.computer?.description){ mutableStateOf(state.computer?.description ?: "")}
    var stock by remember(state.computer?.stock){ mutableStateOf(state.computer?.stock ?: "")}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = title,
                onValueChange = { title = it},
                label = {
                    Text(text = "Title")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = description,
                onValueChange = { description = it},
                label = {
                    Text(text = "Description")
                }
            )


        }

        if (state.error.isNotBlank()){
            Text(
                text = state.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                style = TextStyle(
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            )
        }



        if (state.isLoader){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }else{
            if (state.computer?.id != null){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    onClick = {
                        updateComputer(title, description)

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF035FFF)
                    )
                ) {
                    Text(
                        text = "Update Computer",
                        color = Color.White
                    )
                }

            }else{
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    onClick = {
                        addNewComputer(title, description)


                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF035FFF)
                    )
                ) {
                    Text(
                        text = "Add New Computer",
                        color = Color.White
                    )
                }
            }

        }

    }
}