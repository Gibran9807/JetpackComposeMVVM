package mx.edu.delasalle.mvvmlogin.app.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.edu.delasalle.mvvmlogin.R
import mx.edu.delasalle.mvvmlogin.app.routes.Routes
import mx.edu.delasalle.mvvmlogin.app.routes.Routes.*
import mx.edu.delasalle.mvvmlogin.app.ui.theme.Shapes

@Composable
fun home(navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF035FFF))
    ){
        Scaffold(topBar = { homeToolbar(navController)}, modifier = Modifier ) {
            Body(navController)
        }


    }
}

@Composable
fun homeToolbar(navController: NavController){
    TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
        Text(text = "Components", color = Color.White, textAlign = TextAlign.Left, modifier = Modifier.width(330.dp))
        Text(text = "Login", color = Color.White, modifier = Modifier
            .clickable { navController.navigate(LoginScreen.routes) },
            textAlign = TextAlign.Right
            ) },
        backgroundColor = Color(0xFF035FFF),
        contentColor = Color.White)
}


@Composable
fun Body(navController: NavController){
    Image(
        painter = painterResource(id = R.drawable.background),
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
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nam at lectus urna duis convallis. Consequat id porta nibh venenatis cras sed felis. Id nibh tortor id aliquet lectus. Sit amet nulla facilisi morbi tempus iaculis urna id. Posuere urna nec tincidunt praesent semper. Quis lectus nulla at volutpat diam ut venenatis. Venenatis cras sed felis eget velit aliquet sagittis. Sapien eget mi proin sed libero enim sed faucibus. Cursus vitae congue mauris rhoncus. Tristique magna sit amet purus gravida quis blandit. Posuere sollicitudin aliquam ultrices sagittis orci a. In hendrerit gravida rutrum quisque non tellus orci ac. Enim blandit volutpat maecenas volutpat blandit aliquam etiam. Praesent elementum facilisis leo vel fringilla est.")
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(AboutScreen.routes)
                },
            text = "Acerca de",
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
    
}