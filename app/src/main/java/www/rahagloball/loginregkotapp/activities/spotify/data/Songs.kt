package www.rahagloball.loginregkotapp.activities.spotify.data

import java.io.Serializable

data class Songs(
    val album : Album,
    val name : String,
    val preview_url : String,
): Serializable
