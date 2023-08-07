package www.rahagloball.loginregkotapp.activities.camera

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.*
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.FFmpegExecution
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.otaliastudios.cameraview.*
import com.otaliastudios.cameraview.controls.*
import com.otaliastudios.cameraview.filter.Filters
import com.otaliastudios.cameraview.frame.Frame
import com.otaliastudios.cameraview.frame.FrameProcessor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import www.rahagloball.loginregkotapp.activities.spotify.MyMusicActivity
//import www.rahagloball.loginregkotapp.adapters.SnglChCtsAdapter.context
import java.io.*
import www.rahagloball.loginregkotapp.R


class GoLiveActitivy : AppCompatActivity(), View.OnClickListener, OptionView.Callback {

    companion object {
        private val LOG = CameraLogger.create("DemoApp")
        private const val USE_FRAME_PROCESSOR = false
        private const val DECODE_BITMAP = false
        const val PICK_FILE_REQUEST_CODE = 1002
    }

    private val camera: CameraView by lazy { findViewById(R.id.camera) }
    private val controlPanel: ViewGroup by lazy { findViewById(R.id.controls) }
    private val tvLeftSec: TextView by lazy { findViewById(R.id.tvLeftSec) }
    private val hlProgress: ProgressBar by lazy { findViewById(R.id.hlProgress) }
    private var captureTime: Long = 0

    private var currentFilter = 0
    private val allFilters = Filters.values()

    lateinit var recordLayout: LinearLayout
    lateinit var recordSetting: LinearLayout
    lateinit var addSoundLayout: LinearLayout
    var videoDuration = 31000
    lateinit var video: ImageView
    private lateinit var ivSoundCancel: ImageView
    private lateinit var tvAddSound: TextView
    lateinit var mediaPlayer: MediaPlayer

    private var selectedUri: Uri? = null
    private var realPath: String? = null
    lateinit var fileDescriptor: FileDescriptor
    lateinit var countdownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_live)
        CameraLogger.setLogLevel(CameraLogger.LEVEL_VERBOSE)

        val timerTextView: TextView = findViewById(R.id.tvTimer)
        timerTextView.visibility = View.VISIBLE

        toggleCamera()

        camera.setLifecycleOwner(this)
        camera.addCameraListener(Listener())
        if (USE_FRAME_PROCESSOR) {
            camera.addFrameProcessor(object :
                FrameProcessor {
                private var lastTime = System.currentTimeMillis()
                override fun process(frame: Frame) {
                    val newTime = frame.time
                    val delay = newTime - lastTime
                    lastTime = newTime
                    LOG.v("Frame delayMillis:", delay, "FPS:", 1000 / delay)
                    if (DECODE_BITMAP) {
                        if (frame.format == ImageFormat.NV21
                            && frame.dataClass == ByteArray::class.java
                        ) {
                            val data = frame.getData<ByteArray>()
                            val yuvImage = YuvImage(
                                data,
                                frame.format,
                                frame.size.width,
                                frame.size.height,
                                null
                            )
                            val jpegStream = ByteArrayOutputStream()
                            yuvImage.compressToJpeg(
                                Rect(
                                    0, 0,
                                    frame.size.width,
                                    frame.size.height
                                ), 100, jpegStream
                            )
                            val jpegByteArray = jpegStream.toByteArray()
                            val bitmap = BitmapFactory.decodeByteArray(
                                jpegByteArray,
                                0, jpegByteArray.size
                            )
                            bitmap.toString()
                        }
                    }
                }

            })
        }

        findViewById<View>(R.id.edit).setOnClickListener(this)
        findViewById<View>(R.id.capturePicture).setOnClickListener(this)
        findViewById<View>(R.id.capturePictureSnapshot).setOnClickListener(this)
        findViewById<View>(R.id.captureVideo).setOnClickListener(this)
        findViewById<View>(R.id.captureVideoSnapshot).setOnClickListener(this)
        findViewById<View>(R.id.toggleCamera).setOnClickListener(this)
        findViewById<View>(R.id.changeFilter).setOnClickListener(this)


        val group = controlPanel.getChildAt(0) as ViewGroup
        val watermark = findViewById<View>(R.id.watermark)
        val options: List<Option<*>> = listOf(
//             Layout
            Option.Width(), Option.Height(),
            // Engine and preview
            Option.Mode(), Option.Engine(), Option.Preview(),
            // Some controls
            Option.Flash(), Option.WhiteBalance(), Option.Hdr(),
            Option.PictureMetering(), Option.PictureSnapshotMetering(),
            Option.PictureFormat(),
            // Video recording
            Option.PreviewFrameRate(), Option.VideoCodec(), Option.Audio(), Option.AudioCodec(),
            // Gestures
            Option.Pinch(), Option.HorizontalScroll(), Option.VerticalScroll(),
            Option.Tap(), Option.LongTap(),
//             Watermarks
            Option.OverlayInPreview(watermark),
            Option.OverlayInPictureSnapshot(watermark),
            Option.OverlayInVideoSnapshot(watermark),
//             Frame Processing
            Option.FrameProcessingFormat(),
//             Other
            Option.Grid(), Option.GridColor(), Option.UseDeviceOrientation()
        )


        val dividers = listOf(
            // Layout
            false, true,
            // Engine and preview
            false, false, true,
            // Some controls
            false, false, false, false, false, true,
            // Video recording
            false, false, false, true,
            // Gestures
            false, false, false, false, true,
            // Watermarks
            false, false, true,
            // Frame Processing
            true,
            // Other
            false, false, true
        )
        for (i in options.indices) {
            val view = OptionView<Any>(this)
            view.setOption(options[i] as Option<Any>, this)
            view.setHasDivider(dividers[i])
            group.addView(view, MATCH_PARENT, WRAP_CONTENT)
        }
        controlPanel.viewTreeObserver.addOnGlobalLayoutListener {
            BottomSheetBehavior.from(controlPanel).state = BottomSheetBehavior.STATE_HIDDEN
        }

        // Animate the watermark just to show we record the animation in video snapshots
        val animator = ValueAnimator.ofFloat(1f, 0.8f)
        animator.duration = 300
//        animator.repeatCount = ValueAnimator.INFINITE
//        animator.repeatMode = ValueAnimator.REVERSE
        animator.addUpdateListener { animation ->
            val scale = animation.animatedValue as Float
            watermark.scaleX = scale
            watermark.scaleY = scale
//            watermark.rotation = watermark.rotation + 2
        }
        animator.start()
        camera.mode = Mode.VIDEO

        video = findViewById(R.id.captureVideo)
        val changeDuration = findViewById<View>(R.id.changeDuration)
        val timerLayout = findViewById<View>(R.id.timerLayout)
        val tvCntCancel = findViewById<View>(R.id.tvCntCancel)
        val tvThree = findViewById<View>(R.id.tvThree)
        val tvTen = findViewById<View>(R.id.tvTen)
        val tvTwenty = findViewById<View>(R.id.tvTwenty)
        val btnStartCountdown = findViewById<View>(R.id.btnStartCountdown)
        val ibDuration = findViewById<View>(R.id.ibDuration)
        tvAddSound = findViewById(R.id.tvAddSound)
        ivSoundCancel = findViewById(R.id.ivSoundCancel)

        recordLayout = findViewById(R.id.recordLayout)
        recordSetting = findViewById(R.id.recordSetting)
        addSoundLayout = findViewById(R.id.addSoundLayout)


        changeDuration.setOnClickListener {
            timerLayout.visibility = View.VISIBLE

            recordLayout.visibility = View.INVISIBLE
            recordSetting.visibility = View.INVISIBLE

            val initialTranslationY = 1000f
            val finalTranslationY = 0f
            val anim = ValueAnimator.ofFloat(initialTranslationY, finalTranslationY)
            anim.duration = 500
            anim.addUpdateListener { valueAnimator ->
                timerLayout.translationY = valueAnimator.animatedValue as Float
            }
            anim.start()
        }
        tvCntCancel.setOnClickListener {
            val initialTranslationY = 0f
            val finalTranslationY = 1000f
            val anima = ValueAnimator.ofFloat(initialTranslationY, finalTranslationY)
            anima.duration = 500
            anima.addUpdateListener { valueAnimator ->
                timerLayout.translationY = valueAnimator.animatedValue as Float
            }
            anima.start()

            timerLayout.visibility = View.GONE

            recordLayout.visibility = View.VISIBLE
            recordSetting.visibility = View.VISIBLE


        }

        var seconds = 4000L
        tvThree.setOnClickListener {
            seconds = 4000
            tvThree.setBackgroundResource(R.drawable.countdown_bg_second)
            tvTen.setBackgroundResource(R.color.black_transparent)
            tvTwenty.setBackgroundResource(R.color.black_transparent)
        }
        tvTen.setOnClickListener {
            seconds = 11000
            tvThree.setBackgroundResource(R.color.black_transparent)
            tvTen.setBackgroundResource(R.drawable.countdown_bg_second)
            tvTwenty.setBackgroundResource(R.color.black_transparent)
        }
        tvTwenty.setOnClickListener {
            seconds = 21000
            tvThree.setBackgroundResource(R.color.black_transparent)
            tvTen.setBackgroundResource(R.color.black_transparent)
            tvTwenty.setBackgroundResource(R.drawable.countdown_bg_second)
        }


        btnStartCountdown.setOnClickListener {
//            startCountDownFor(seconds)
//            timerLayout.visibility = View.GONE
        }
        ibDuration.setOnClickListener {
            ibDuration.isSelected = !ibDuration.isSelected
            videoDuration = if (!ibDuration.isSelected) 31000 else 61000

        }
        tvAddSound.setOnClickListener {
            pickFileFromStorage()
        }
        mediaPlayer = MediaPlayer()

        countdownTimer = object : CountDownTimer(videoDuration.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                tvLeftSec.text = timeLeft.toString()


                hlProgress.progress = ((videoDuration / 1000 - timeLeft.toInt()) * 3.33).toInt()
            }

            override fun onFinish() {
                tvLeftSec.text = "0"
            }
        }


    }

    private fun startCountDownFor(s: Long) {

        val timerTextView: TextView = findViewById(R.id.tvTimer)
        val stopTimer: ImageView = findViewById(R.id.stopTimer)


        timerTextView.visibility = View.VISIBLE
        stopTimer.visibility = View.VISIBLE
        val countDownTimer = object : CountDownTimer(s, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerTextView.text = "$secondsLeft"

                recordLayout.visibility = View.INVISIBLE
                recordSetting.visibility = View.INVISIBLE


            }

            override fun onFinish() {
//                timerTextView.visibility = View.INVISIBLE
                stopTimer.visibility = View.INVISIBLE

                recordLayout.visibility = View.VISIBLE
                recordSetting.visibility = View.VISIBLE

                captureVideoSnapshot()
            }
        }.start()

        stopTimer.setOnClickListener {
            countDownTimer.cancel()
//            timerTextView.visibility = View.INVISIBLE
            stopTimer.visibility = View.INVISIBLE

            recordLayout.visibility = View.VISIBLE
            recordSetting.visibility = View.VISIBLE
        }

    }

    private fun message1() {

    }

    private fun message(content: String, important: Boolean) {
        if (important) {
//            LOG.w(content)
//            Toast.makeText(this, content, Toast.LENGTH_LONG).show()
        } else {
//            LOG.i(content)
//            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        }
    }

    private inner class Listener : CameraListener() {
        override fun onCameraOpened(options: CameraOptions) {
            val group = controlPanel.getChildAt(0) as ViewGroup
            for (i in 0 until group.childCount) {
                val view = group.getChildAt(i) as OptionView<*>
                view.onCameraOpened(camera, options)
            }
        }

        override fun onCameraError(exception: CameraException) {
            super.onCameraError(exception)
            message("Got CameraException #" + exception.reason, true)
        }

        override fun onPictureTaken(result: PictureResult) {

            super.onPictureTaken(result)
            if (camera.isTakingVideo) {
                message("Captured while taking video. Size=" + result.size, false)
                return
            }

            // This can happen if picture was taken with a gesture.
            val callbackTime = System.currentTimeMillis()
            if (captureTime == 0L) captureTime = callbackTime - 300
            LOG.w("onPictureTaken called! Launching activity. Delay:", callbackTime - captureTime)
            PicturePreviewActivity.pictureResult = result
            val intent = Intent(this@GoLiveActitivy, PicturePreviewActivity::class.java)
            intent.putExtra("delay", callbackTime - captureTime)
            startActivity(intent)
            captureTime = 0
            LOG.w("onPictureTaken called! Launched activity.")
        }

        override fun onVideoTaken(result: VideoResult) {
            super.onVideoTaken(result)
            LOG.w("onVideoTaken called! Launching activity.")
            VideoPreviewActivity.videoResult = result
            hlProgress.progress = 0

            runBlocking {
                delay(3000)
                val intent = Intent(this@GoLiveActitivy, VideoPreviewActivity::class.java)
                startActivity(intent)
            }

            LOG.w("onVideoTaken called! Launched activity.")
        }

        override fun onVideoRecordingStart() {
            super.onVideoRecordingStart()
            mediaPlayer.start()
            tvAddSound.text = "Add sound"
            addSoundLayout.visibility = View.INVISIBLE
            LOG.w("onVideoRecordingStart!")
            countdownTimer.start()
            tvLeftSec.visibility = View.VISIBLE
        }

        override fun onVideoRecordingEnd() {
            super.onVideoRecordingEnd()
            // message("Video taken. Processing...", false)
            LOG.w("onVideoRecordingEnd!")
            recordSetting.visibility = View.VISIBLE
            addSoundLayout.visibility = View.VISIBLE
            video.isSelected = false
            mediaPlayer.stop()

            if (selectedUri != null) {
                mergeAudio()
                VideoPreviewActivity.audioMerged = true
            } else {
                VideoPreviewActivity.audioMerged = false
            }
            countdownTimer.cancel()

            tvLeftSec.visibility = View.INVISIBLE

        }

        override fun onExposureCorrectionChanged(
            newValue: Float,
            bounds: FloatArray,
            fingers: Array<PointF>?
        ) {
            super.onExposureCorrectionChanged(newValue, bounds, fingers)
            //message("Exposure correction:$newValue", false) Toast exposure
        }

        override fun onZoomChanged(newValue: Float, bounds: FloatArray, fingers: Array<PointF>?) {
            super.onZoomChanged(newValue, bounds, fingers)
            //message("Zoom:$newValue", false) Zoom toast
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.edit -> edit()
            R.id.capturePicture -> capturePicture()
            R.id.capturePictureSnapshot -> capturePictureSnapshot()
            R.id.captureVideo -> {
                captureVideoSnapshot()

            } //captureVideo()
            R.id.captureVideoSnapshot -> captureVideoSnapshot()
            R.id.toggleCamera -> toggleCamera()
            R.id.changeFilter -> changeCurrentFilter()
        }
    }

    override fun onBackPressed() {
        val b = BottomSheetBehavior.from(controlPanel)
        if (b.state != BottomSheetBehavior.STATE_HIDDEN) {
            b.state = BottomSheetBehavior.STATE_HIDDEN
            return
        }
        super.onBackPressed()
    }

    private fun edit() {
        BottomSheetBehavior.from(controlPanel).state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun capturePicture() {
        if (camera.mode == Mode.VIDEO) return run {
            message("Can't take HQ pictures while in VIDEO mode.", false)
        }
        if (camera.isTakingPicture) return
        captureTime = System.currentTimeMillis()
        message("Capturing picture...", false)
        camera.takePicture()
    }

    private fun capturePictureSnapshot() {
        if (camera.isTakingPicture) return
        if (camera.preview != Preview.GL_SURFACE) return run {
            message("Picture snapshots are only allowed with the GL_SURFACE preview.", true)
        }
        captureTime = System.currentTimeMillis()
        message("Capturing picture snapshot...", false)
        camera.takePictureSnapshot()
    }

    private fun captureVideo() {
        if (camera.mode == Mode.PICTURE) return run {
            message("Can't record HQ videos while in PICTURE mode.", false)
        }
        if (camera.isTakingPicture || camera.isTakingVideo) return
        message("Recording for 30 seconds...", true)
        camera.takeVideo(File(filesDir, "video.mp4"), 30000)
    }

    private fun captureVideoSnapshot() {

        video.isSelected = !video.isSelected
        recordSetting.visibility = View.INVISIBLE

        if (camera.isTakingVideo) return run {
            //message("Already taking video.", false)
            camera.stopVideo()
        }
        if (camera.preview != Preview.GL_SURFACE) return run {
            message("Video snapshots are only allowed with the GL_SURFACE preview.", true)
        }
        //message("Recording cuties for 30 seconds...", true)
        camera.takeVideoSnapshot(File(filesDir, "video.mp4"), videoDuration - 1000)
    }

    private fun toggleCamera() {
        if (camera.isTakingPicture || camera.isTakingVideo) return
        when (camera.toggleFacing()) {
//            Facing.BACK -> message("Switched to back camera!", false)
            Facing.FRONT -> message1()
            else -> {}
        }
    }

    private fun changeCurrentFilter() {
        if (camera.preview != Preview.GL_SURFACE) return run {
            message("Filters are supported only when preview is Preview.GL_SURFACE.", true)
        }
        if (currentFilter < allFilters.size - 1) {
            currentFilter++
        } else {
            currentFilter = 0
        }
        val filter = allFilters[currentFilter]
        //message(filter.toString(), false) //Toast for filter name when changed

        // Normal behavior:
        camera.filter = filter.newInstance()

        // To test MultiFilter:
        // DuotoneFilter duotone = new DuotoneFilter();
        // duotone.setFirstColor(Color.RED);
        // duotone.setSecondColor(Color.GREEN);
        // camera.setFilter(new MultiFilter(duotone, filter.newInstance()));
    }

    override fun <T : Any> onValueChanged(option: Option<T>, value: T, name: String): Boolean {
        if (option is Option.Width || option is Option.Height) {
            val preview = camera.preview
            val wrapContent = value as Int == WRAP_CONTENT
            if (preview == Preview.SURFACE && !wrapContent) {
                message(
                    "The SurfaceView preview does not support width or height changes. " +
                            "The view will act as WRAP_CONTENT by default.", true
                )
                return false
            }
        }
        option.set(camera, 22 as T)
        BottomSheetBehavior.from(controlPanel).state = BottomSheetBehavior.STATE_HIDDEN
        //Fro message("Changed " + option.name + " to " + value, false)
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val valid = grantResults.all { it == PERMISSION_GRANTED }
        if (valid && !camera.isOpened) {
            camera.open()
        }
    }


    private fun pickFileFromStorage() {
        val intent = Intent(this@GoLiveActitivy, MyMusicActivity::class.java)
        startActivity(intent)
    }
//
//    private fun pickFileFromStorage() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        val uri: Uri = Uri.parse(Environment.getExternalStorageState())
//        intent.setDataAndType(uri, "audio/*")
//        startActivityForResult(Intent.createChooser(intent, "Open internal storage"), PICK_FILE_REQUEST_CODE)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {

            selectedUri = data?.data

            val contentResolver = this.contentResolver
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedUri!!, "r")
            fileDescriptor = parcelFileDescriptor?.fileDescriptor!!

            camera.audio = Audio.OFF
            mediaPlayer.setDataSource(fileDescriptor)
            mediaPlayer.prepare()
            mediaPlayer.isLooping = true

            val cursor = contentResolver.query(selectedUri!!, null, null, null, null)
            val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)

            cursor?.moveToFirst()
            val fileName = cursor?.getString(nameIndex!!)

            val documentFile = DocumentFile.fromSingleUri(this, selectedUri!!)
            val path = documentFile?.uri?.path

            val newString = path?.replace("/document/primary:", "/storage/emulated/0/")

            realPath = newString

            tvAddSound.text = fileName

            cursor?.close()


        }
    }

//    private fun getsonginfo() {
//
//
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.reset()
        mediaPlayer.stop()
    }

    private fun mergeAudio() {
        val c = arrayOf(
            "-i",
            "$filesDir/video.mp4",
            "-i",
            realPath!!,
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
        mergeVideo(c)
    }


    private fun mergeVideo(co: Array<String>) {
        FFmpeg.executeAsync(co) { executionId, returnCode ->
            Log.d("hello", "return  $returnCode")
            Log.d("hello", "executionID  $executionId")
            Log.d("hello", "FFMPEG  " + FFmpegExecution(executionId, co))
        }
    }


    override fun onStart() {
        super.onStart()
        val directory = File(filesDir.toString())
        if (directory.exists() && directory.isDirectory) {
            directory.listFiles()?.forEach { it.deleteRecursively() }
        }

    }

    private fun getPathFromUri(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.MediaColumns.DATA)

        // Query the MediaStore to get the file path
        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                filePath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
            }
        }

        return filePath
    }


}
