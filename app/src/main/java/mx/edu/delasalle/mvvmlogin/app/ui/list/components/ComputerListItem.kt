package mx.edu.delasalle.mvvmlogin.app.ui.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import mx.edu.delasalle.mvvmlogin.app.model.Computer

@ExperimentalMaterialApi
@Composable
fun ComputerListItem(
    computer: Computer,
    onItemClick: (String) -> Unit
) {
    Card(
        elevation = 0.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable {
                    onItemClick(computer.id)
                }
        ){
            Image(
                painter = rememberImagePainter(computer.imageURL),
                contentDescription = "",
                modifier = Modifier
                    .width(100.dp)
                    .height(160.dp)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text =  computer.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = computer.description,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = "Start Icon",
                        tint = Color.White
                    )

                    Text(
                        text = computer.rating.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        text = "${computer.stock} Stock",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}