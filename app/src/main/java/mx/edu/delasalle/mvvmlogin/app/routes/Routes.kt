package mx.edu.delasalle.mvvmlogin.app.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

sealed class Routes(
    val routes: String,
    val arguments: List<NamedNavArgument>
) {
    object Home: Routes("home", emptyList())
    object LoginScreen: Routes("LoginScreen", emptyList())
    object ComputerScreen: Routes( "ComputerScreen", emptyList())
    object ComputerDetailScreen: Routes(
        routes = "ComputerDetailScreen",
        arguments = listOf(
            navArgument("computerId"){
                nullable = true
            }
        )
    )
    object AboutScreen: Routes("AboutScreen", emptyList())
}