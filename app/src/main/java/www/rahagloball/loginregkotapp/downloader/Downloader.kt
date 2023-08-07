package www.rahagloball.loginregkotapp.downloader

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

@OptIn(DelicateCoroutinesApi::class)
fun downloadFile(url: String, destinationFile: File, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val connection = URL(url).openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            val outputStream = FileOutputStream(destinationFile)

            val buffer = ByteArray(4 * 1024)
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()

            withContext(Dispatchers.Main) {
                onSuccess()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.localizedMessage?.let { onFailure(it) }
            }
        }
    }
}