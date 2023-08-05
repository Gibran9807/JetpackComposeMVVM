package mx.edu.delasalle.mvvmlogin.app.ui.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import mx.edu.delasalle.mvvmlogin.R
import mx.edu.delasalle.mvvmlogin.app.presentation.Login.LoginViewModel
import mx.edu.delasalle.mvvmlogin.app.routes.Routes.*


@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Login(Modifier.align(Alignment.Center), viewModel, navController)
    }
}


@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {

    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnable : Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else  {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable){
                coroutineScope.launch {
                    viewModel.onLoginSelected(navController)
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            SingUpButton(loginEnable, navController) {
                coroutineScope.launch {
                    viewModel.signUpButton()
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            exit(navController = navController)
        }
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), 
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar Sesion")
    }
}

@Composable
fun PasswordField(password: String, onTextFieldChanged:(String) -> Unit) {
    TextField(
        value = password,
        onValueChange = {onTextFieldChanged(it)},
        placeholder = { Text(text = "Contraseña")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmailField(email: String, onTextFieldChanged:(String) -> Unit) {

    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier : Modifier) {
    Image(painter = painterResource(id = R.drawable.pc), contentDescription = "Logo", modifier = modifier)
}

@Composable
fun SingUpButton(loginEnable: Boolean, navController: NavController ,SignUpButton : () -> Unit) {
    Button(
        onClick = { SignUpButton() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF035FFF),
            disabledBackgroundColor = Color(0xFF383D9E),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Registrarse")
    }
}

@Composable
fun exit(navController: NavController){
    Text(text = "Regresar", color = Color.Gray ,modifier = Modifier
        .clickable { navController.navigate(Home.routes) }
    )
}
