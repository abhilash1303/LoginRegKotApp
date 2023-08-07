package www.rahagloball.loginregkotapp.configuration


import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

open class DownloadVideoAsyncTask(val mContext: Context, val mFileName: String) :
    AsyncTask<String?, Int?, String?>() {

    override fun doInBackground(vararg params: String?): String? {
        lateinit var input: InputStream
        lateinit  var output: OutputStream
        lateinit  var connection: HttpURLConnection
        try {
            val url = params[0]?.let { URL(it) }
            connection = (url?.openConnection() as? HttpURLConnection)!!
            connection.connect()

            // Expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                return ("Server returned HTTP " + connection.responseCode
                        + " " + connection.responseMessage)
            }

            // Create a new file in the Downloads directory
            val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(downloadsDirectory, mFileName)

            input = connection.inputStream
            output = FileOutputStream(file)

            val data = ByteArray(4096)
            var total: Long = 0
            var count: Int
            while (input?.read(data).also { count = it ?: -1 } != -1) {
                if (isCancelled) {
                    input?.close()
                    return null
                }
                total += count.toLong()
                publishProgress((total * 100 / connection.contentLength).toInt())
                output.write(data, 0, count)
            }

            // Add the downloaded video to the media store for visibility in other apps
            val mediaStoreUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.Video.Media.DISPLAY_NAME, mFileName)
                    put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                    put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
                }
                mContext.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
            } else {
                // For older devices, scan the file to make it visible in the media store
                val mediaScannerIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                mediaScannerIntent.data = Uri.fromFile(file)
                mContext.sendBroadcast(mediaScannerIntent)
                null
            }

            Log.d("DownloadVideoAsyncTask", "Video downloaded and saved: $file")

            return null
        } catch (e: Exception) {
            return e.toString()
        } finally {
            try {
                output.close()
                input.close()
            } catch (ignored: IOException) {
            }
            connection.disconnect()
        }
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val progress = values[0] ?: return
        Log.d("DownloadVideoAsyncTask", "Download progress: $progress%")
        // Update your progress UI here if needed
    }

    override fun onPostExecute(s: String?) {
        super.onPostExecute(s)
        // Handle the completion of the download here if needed
    }
}


//class DownloadVideoAsyncTask(private val mContext: Context, private val mFileName: String) :
//    AsyncTask<String?, Int?, String?>() {
//
//
//    override fun onPreExecute() {
//        super.onPreExecute()
//    }
//
//    protected override fun doInBackground(vararg params: String?): String? {
//        var input: InputStream
//        var output: OutputStream
//        var connection: HttpURLConnection
//        try {
//            val url = URL(params[0])
//            connection = url.openConnection() as HttpURLConnection
//            connection.connect()
//
//            // expect HTTP 200 OK, so we don't mistakenly save error report
//            // instead of the file
//            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
//                return ("Server returned HTTP " + connection.responseCode
//                        + " " + connection.responseMessage)
//            }
//
//            // this will be useful to display download percentage
//            // might be -1: server did not report the length
//            val fileLength = connection.contentLength
//
//            // download the file
//            input = connection.inputStream
//
////            output = new FileOutputStream("/data/data/com.example.vadym.test1/textfile.txt");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                output =
//                    Files.newOutputStream(Paths.get(mContext.cacheDir.toString() + "/" + mFileName))
//            }
//            val data = ByteArray(4096)
//            var total: Long = 0
//            var count: Int
//            while (input.read(data).also { count = it } != -1) {
//                // allow canceling with back button
//                if (isCancelled) {
//                    input.close()
//                    return null
//                }
//                total += count.toLong()
//                // publishing the progress....
//                if (fileLength > 0) // only if total length is known
//                    publishProgress((total * 100 / fileLength).toInt())
//                output!!.write(data, 0, count)
//            }
//        } catch (e: Exception) {
//            return e.toString()
//        } finally {
//            try {
//                output?.close()
//                input?.close()
//            } catch (ignored: IOException) {
//            }
//            connection?.disconnect()
//        }
//        return null
//    }
//
//    protected override fun onProgressUpdate(vararg values: Int?) {
//        super.onProgressUpdate(*values)
//        Log.d("ptg", "onProgressUpdate: " + values[0])
//
//        // Update the progress of the write request if needed
//        val progress = values[0] ?: return
//        val contentResolver = mContext.contentResolver
//        contentResolver.update(writeRequest.uri, ContentValues().apply {
//            put(MediaStore.Video.Media.UPLOAD_PROGRESS, progress)
//        }, null, null)
//    }
//
////    protected override fun onProgressUpdate(vararg values: Int?) {
////        super.onProgressUpdate(*values)
////        Log.d("ptg", "onProgressUpdate: " + values[0])
////    }
//
//    override fun onPostExecute(s: String?) {
//        super.onPostExecute(s)
//    }
//}