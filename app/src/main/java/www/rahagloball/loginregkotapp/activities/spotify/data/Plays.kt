package www.rahagloball.loginregkotapp.activities.spotify.data

import java.io.Serializable

data class Plays(
    val songUrl: String,
    val title:String,
    var imageUri : String,
    var isSelected: Boolean = false,
    val id : Int = 0,

    ):Serializable
