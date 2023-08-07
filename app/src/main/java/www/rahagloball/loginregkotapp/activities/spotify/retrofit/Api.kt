package www.rahagloball.loginregkotapp.activities.spotify.retrofit


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import www.rahagloball.loginregkotapp.activities.spotify.data.SongResponse

interface Api {
    @GET("get-search-track/")
    suspend fun getSongs(
        @Query("search")  search: String,
    ) : Response<SongResponse>

}