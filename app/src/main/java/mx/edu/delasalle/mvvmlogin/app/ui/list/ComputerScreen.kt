package mx.edu.delasalle.mvvmlogin.app.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import mx.edu.delasalle.mvvmlogin.app.routes.Routes
import mx.edu.delasalle.mvvmlogin.app.states.ComputerListState
import mx.edu.delasalle.mvvmlogin.app.ui.list.components.ComputerList

@ExperimentalMaterialApi
@Composable
fun ComputerScreen(
    state: ComputerListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    navigationController: NavController,
    onItemClick: (String) -> Unit,
    deleteComputer: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigationController.navigate(Routes.ComputerDetailScreen.routes) },
                backgroundColor = Color(0xFFFF4303),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
            }
        }
    ) {
        ComputerList(
            state = state,
            isRefreshing = isRefreshing,
            refreshData = refreshData,
            onItemClick = onItemClick,
            deleteComputer = deleteComputer
        )
    }
}