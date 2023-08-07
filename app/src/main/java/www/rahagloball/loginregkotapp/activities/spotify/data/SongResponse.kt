package www.rahagloball.loginregkotapp.activities.spotify.data

import java.io.Serializable

data class SongResponse(
    val data : Tracks,
    val id : Int = 0
    ): Serializable