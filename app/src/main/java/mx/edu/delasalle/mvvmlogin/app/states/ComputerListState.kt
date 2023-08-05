package mx.edu.delasalle.mvvmlogin.app.states

import mx.edu.delasalle.mvvmlogin.app.model.Computer

data class ComputerListState(
    val isLoading: Boolean = false,
    val computer: List<Computer> = emptyList(),
    val error: String = ""
)
