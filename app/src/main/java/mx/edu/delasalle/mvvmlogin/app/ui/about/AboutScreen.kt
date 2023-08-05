package mx.edu.delasalle.mvvmlogin.app.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import mx.edu.delasalle.mvvmlogin.R
import mx.edu.delasalle.mvvmlogin.app.routes.Routes

@Composable
fun AboutScreen(navController: NavController){

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF035FFF))
    ){
        Scaffold(topBar = { homeToolbar(navController)}, modifier = Modifier ) {
            Body()
        }


    }
}

@Composable
fun homeToolbar(navController: NavController){
    TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Atras",
            modifier = Modifier.clickable {
                navController.navigate(Routes.Home.routes)
            }
        )},
        backgroundColor = Color(0xFF035FFF),
        contentColor = Color.White)
}


@Composable
fun Body() {
    Image(
        painter = painterResource(id = R.drawable.about),
        contentDescription = "",
        contentScale = ContentScale.FillBounds
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(color = Color.White,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nam at lectus urna duis convallis. Consequat id porta nibh venenatis cras sed felis. Id nibh tortor id aliquet lectus. Sit amet nulla facilisi morbi tempus iaculis urna id. Posuere urna nec tincidunt praesent semper. Quis lectus nulla at volutpat diam ut venenatis. Venenatis cras sed felis eget velit aliquet sagittis. Sapien eget mi proin sed libero enim sed faucibus. Cursus vitae congue mauris rhoncus. Tristique magna sit amet purus gravida quis blandit. Posuere sollicitudin aliquam ultrices sagittis orci a. In hendrerit gravida rutrum quisque non tellus orci ac. Enim blandit volutpat maecenas volutpat blandit aliquam etiam. Praesent elementum facilisis leo vel fringilla est.")
    }

}