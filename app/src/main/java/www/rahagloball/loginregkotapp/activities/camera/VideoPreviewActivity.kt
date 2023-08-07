package www.rahagloball.loginregkotapp.activities.camera

import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.FFmpegExecution
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.size.AspectRatio
import www.rahagloball.loginregkotapp.R


class VideoPreviewActivity : AppCompatActivity() {
    companion object {
        var videoResult: VideoResult? = null
        var audioMerged = false
        var audioPath : String? = null
    }

    private val videoView: VideoView by lazy { findViewById<VideoView>(R.id.video) }
    private var pathUri = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_preview)
        val result = videoResult ?: run {
            finish()
            return
        }


        videoView.setOnClickListener { playVideo() }
        val actualResolution = findViewById<MessageView>(R.id.actualResolution)
        val isSnapshot = findViewById<MessageView>(R.id.isSnapshot)
        val rotation = findViewById<MessageView>(R.id.rotation)
        val audio = findViewById<MessageView>(R.id.audio)
        val audioBitRate = findViewById<MessageView>(R.id.audioBitRate)
        val videoCodec = findViewById<MessageView>(R.id.videoCodec)
        val audioCodec = findViewById<MessageView>(R.id.audioCodec)
        val videoBitRate = findViewById<MessageView>(R.id.videoBitRate)
        val videoFrameRate = findViewById<MessageView>(R.id.videoFrameRate)

        val ratio = AspectRatio.of(result.size)
        actualResolution.setTitleAndMessage("Size", "${result.size} ($ratio)")
        isSnapshot.setTitleAndMessage("Snapshot", result.isSnapshot.toString())
        rotation.setTitleAndMessage("Rotation", result.rotation.toString())
        audio.setTitleAndMessage("Audio", result.audio.name)
        audioBitRate.setTitleAndMessage("Audio bit rate", "${result.audioBitRate} bits per sec.")
        videoCodec.setTitleAndMessage("VideoCodec", result.videoCodec.name)
        audioCodec.setTitleAndMessage("AudioCodec", result.audioCodec.name)
        videoBitRate.setTitleAndMessage("Video bit rate", "${result.videoBitRate} bits per sec.")
        videoFrameRate.setTitleAndMessage("Video frame rate", "${result.videoFrameRate} fps")

        if (audioMerged){
            mergeAudio(audioPath!!)
            pathUri = "$filesDir/video2.mp4"
        }
        else{
            pathUri = "$filesDir/video.mp4"
            setMedia()
        }


//        videoView.setOnPreparedListener { mp ->
//            val lp = videoView.layoutParams
//            val videoWidth = mp.videoWidth.toFloat()
//            val videoHeight = mp.videoHeight.toFloat()
//            val viewWidth = videoView.width.toFloat()
//            lp.height = (viewWidth * (videoHeight / videoWidth)).toInt()
//            videoView.layoutParams = lp
//           // playVideo()
//            if (result.isSnapshot) {
//                // Log the real size for debugging reason.
//                Log.e("VideoPreview", "The video full size is " + videoWidth + "x" + videoHeight)
//            }
//        }

        val btnVdoCancel = findViewById<Button>(R.id.btnVdoCancel)
        val btnVdoUpload = findViewById<Button>(R.id.btnVdoUpload)

        btnVdoCancel.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
        btnVdoUpload.setOnClickListener {
           // uploadVideo()
//            val intent = Intent(this, TUploadVidNewActivity1::class.java)
//            startActivity(intent)

//            Log.d("TAG", "onCreate: "+token)

            val intent = Intent(this, UploadVideoActivity::class.java)
            intent.putExtra("key", pathUri)
            startActivity(intent)

        }
        animateTop()


    }

    private fun playVideo() {
        if (!videoView.isPlaying) {
            videoView.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            videoResult = null
        }
         audioMerged = false
        audioPath = null
    }

    fun showProgress(boolean: Boolean){
        val progressBar2 = findViewById<ProgressBar>(R.id.progressBar2)
        val tvUploading = findViewById<View>(R.id.tvUploading)
        val uploadSection = findViewById<View>(R.id.uploadSection)



        if (boolean){
            progressBar2.visibility = View.VISIBLE
            tvUploading.visibility = View.VISIBLE
            uploadSection.visibility = View.INVISIBLE

        }else{

            progressBar2.visibility = View.INVISIBLE
            tvUploading.visibility = View.INVISIBLE
            uploadSection.visibility = View.VISIBLE
        }


    }
    private fun animateTop(){
        val uploadSection = findViewById<View>(R.id.uploadSection)
        val initialTranslationY = -100f
        val finalTranslationY = 0f
        val anim = ValueAnimator.ofFloat(initialTranslationY, finalTranslationY)
        anim.duration = 500
        anim.addUpdateListener { valueAnimator ->
            uploadSection.translationY = valueAnimator.animatedValue as Float
        }
        anim.start()
    }
    private fun mergeAudio(audioPath:String) {

        val c = arrayOf(
            "-i",
            "$filesDir/video.mp4",
            "-i",
            audioPath,
            "-c:v",
            "copy",
            "-c:a",
            "aac",
            "-map",
            "0:v:0",
            "-map",
            "1:a:0",
            "-shortest",
            "$filesDir/video2.mp4"
        )
        FFmpeg.executeAsync(c) { executionId, returnCode ->
            Log.d("hello", "return  $returnCode")
            Log.d("hello", "executionID  $executionId")
            Log.d("hello", "FFMPEG  " + FFmpegExecution(executionId, c))

            if (returnCode==0){
                FFmpeg.cancel()
                setMedia()
            }else{
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun setMedia(){
        val controller = MediaController(this@VideoPreviewActivity)
        controller.setAnchorView(videoView)
        controller.setMediaPlayer(videoView)
        videoView.setMediaController(controller)
        videoView.setVideoURI(Uri.parse(pathUri))
        playVideo()
    }



}