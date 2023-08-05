package mx.edu.delasalle.mvvmlogin.app.model

data class Computer(
    val id: String,
    val imageURL: String,
    val title: String,
    val description: String,
    val rating: Float,
    val stock: Int
){
    constructor(): this("", "", "", "", 0.0F, 0)
}
