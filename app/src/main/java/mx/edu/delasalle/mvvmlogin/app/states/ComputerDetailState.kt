package mx.edu.delasalle.mvvmlogin.app.states

import mx.edu.delasalle.mvvmlogin.app.model.Computer

data class ComputerDetailState (
    val isLoader: Boolean = false,
    val computer: Computer? = null,
    val error: String = ""
)