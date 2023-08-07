package www.rahagloball.loginregkotapp.activities.spotify

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.camera.CameraActivity
import www.rahagloball.loginregkotapp.activities.spotify.adapter.SongAdapter
import www.rahagloball.loginregkotapp.activities.spotify.data.Plays
import www.rahagloball.loginregkotapp.activities.spotify.retrofit.Api
import www.rahagloball.loginregkotapp.activities.spotify.retrofit.RetrofitHelper
import www.rahagloball.loginregkotapp.downloader.downloadFile
import java.io.File
import java.io.FileDescriptor

class MyMusicActivity : AppCompatActivity() {

    val list = mutableListOf<Plays>()
    var mediaPlayer = MediaPlayer()
    lateinit var parcelFileDescriptor : ParcelFileDescriptor
    lateinit var fileDescriptor : FileDescriptor
    lateinit var myAdapter : SongAdapter
    lateinit var progressBar3 : ProgressBar
    lateinit var nestedScroll : NestedScrollView
    lateinit var songRv : RecyclerView
    lateinit var viewCancel : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

         songRv  = findViewById(R.id.songRv)
        val btnSongSearch : Button = findViewById(R.id.btnSongSearch)
        progressBar3  = findViewById(R.id.progressBar3)
        nestedScroll  = findViewById(R.id.nestedScroll)
        viewCancel  = findViewById(R.id.viewCancel)

        btnSongSearch.setOnClickListener {
            val etSongSearch : EditText = findViewById(R.id.etSongSearch)
           val searchQuery =  etSongSearch.text.trim().toString()
            fetchFromServer(searchQuery)
            viewCancel.visibility = View.VISIBLE


        }
        viewCancel.setOnClickListener {
            viewCancel.visibility = View.GONE
            fetchFromServer("hindi")

        }

        fetchFromServer("hindi")

    }
    private fun fetchFromServer(query:String){
        progressBar3.visibility = View.VISIBLE
        nestedScroll.visibility = View.INVISIBLE

        val quotesApi = RetrofitHelper.getInstance().create(Api::class.java)
        GlobalScope.launch {
            val result = quotesApi.getSongs(query)
            if (result.isSuccessful) {
                val myList = result.body()
//                Log.d("lister: ", result.body().toString())

                runOnUiThread{
                    list.clear()
                    myList?.data?.tracks?.items?.forEach {
                        list.add(Plays(it.preview_url,it.name,it.album.images[0].url))

                    }
                    progressBar3.visibility = View.INVISIBLE
                    nestedScroll.visibility = View.VISIBLE

                     myAdapter = SongAdapter(list, object : SongAdapter.OnItemClickListener {
                        override fun onItemClick(plays: Plays, position : Int) {


                            list.clear()
                            myList?.data?.tracks?.items?.forEach {
                                list.add(Plays(it.preview_url,it.name,it.album.images[0].url))

                            }
                            list[position].isSelected = true
                            myAdapter.notifyDataSetChanged()

                            val  path = "$cacheDir/playing.mp3"
                            val file = File(path)
                            if (file.exists()) file.delete()

                            downloadFile(
                                plays.songUrl,
                                file,
                                onSuccess = {
                                    runOnUiThread {
                                        if (mediaPlayer.isPlaying) mediaPlayer.stop()

                                        mediaPlayer = MediaPlayer()

                                        parcelFileDescriptor = contentResolver.openFileDescriptor(file.toUri(), "r")!!
                                        fileDescriptor = parcelFileDescriptor.fileDescriptor
                                        mediaPlayer.setDataSource(fileDescriptor)
                                        mediaPlayer.prepare()
                                        mediaPlayer.isLooping = true
                                        mediaPlayer.start()
                                    }
                                },
                                onFailure = {
                                    Log.d("TAG", "Download failure: $it")
                                }
                            )
                        }

                         override fun onCheckClick(plays: Plays) {
                             mediaPlayer.stop()
                             CameraActivity.isSpotifySelected = true
                             CameraActivity.songName = plays.title
                             val intent = Intent(this@MyMusicActivity, CameraActivity::class.java)
                             this@MyMusicActivity.startActivity(intent)
                             Log.d("TAG", "Boom Boom")
                         }
                     })


                    songRv.apply {
                        adapter = myAdapter
                        layoutManager = LinearLayoutManager(applicationContext)
                        setHasFixedSize(true)
                    }

                    myAdapter.notifyDataSetChanged()

                }



            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }


}