package www.rahagloball.loginregkotapp.models.flagsss

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

object FlagUtils {
    fun convertUnicodeToFlagImage(context: Context?, unicodeNotation: String): Bitmap? {
        val countryCode =
            unicodeNotation.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (countryCode.size != 2) {
            return null
        }

        // Combine the country code Unicode characters
        val combinedCharacters = StringBuilder()
            .append(Character.toChars(countryCode[0].substring(2).toInt(16)))
            .append(Character.toChars(countryCode[1].substring(2).toInt(16)))
            .toString()

        // Set up Paint object for drawing
        val textSize = 100f
        val padding = 10f
        //        Typeface font = Typeface.createFromAsset(context.getAssets(), "path_to_your_font_file.ttf");
        val paint = Paint()
        paint.textSize = textSize
        //        paint.setTypeface(font);

        // Calculate the size of the image based on the text
        val bounds = Rect()
        paint.getTextBounds(combinedCharacters, 0, combinedCharacters.length, bounds)
        val textWidth = bounds.width()
        val textHeight = bounds.height()

        // Create a Bitmap with a white background
        val image = Bitmap.createBitmap(
            (textWidth + padding).toInt(),
            (textHeight + padding).toInt(),
            Bitmap.Config.ARGB_8888
        )

        // Create a Canvas object for drawing on the Bitmap
        val canvas = Canvas(image)
        canvas.drawColor(Color.WHITE)

        // Draw the Unicode characters on the Bitmap
        canvas.drawText(combinedCharacters, padding / 2, textHeight + padding / 2, paint)
        return image
    }
}