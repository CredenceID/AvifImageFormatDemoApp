package com.credenceid.avifimageformattest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.credenceid.avifimageformattest.ui.theme.AvifImageFormatTestTheme
import com.credenceid.avifimageformattest.util.decodeAvifFile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stream = baseContext.assets.open("avif_art.avif")
        val avisImageRawBytes = ByteArray(stream.available())
        stream.read(avisImageRawBytes)
        stream.close()
        val avifDecodedBitmap = decodeAvifFile(imageRawBytes = avisImageRawBytes, context = baseContext)

        enableEdgeToEdge()
        setContent {
            AvifImageFormatTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayImages(
                        BitmapFactory.decodeResource(baseContext.getResources(),
                            R.drawable.credence_id_logo),
                        avifDecodedBitmap
                    )
                }
            }
        }
    }
}


@Composable
fun DisplayImages(bitmap: Bitmap, bitmapAvis: Bitmap) {
    Column (
        modifier = Modifier.padding((PaddingValues(top = 60.dp))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This is a JPEG image format displayed",
            fontSize = 30.sp
        )
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "some useful description",
        )
        Text(

            modifier = Modifier.padding((PaddingValues(top = 60.dp))),
            text = "This is a AVIS image format displayed",
            fontSize = 30.sp
        )
        Image(
            bitmap = bitmapAvis.asImageBitmap(),
            contentDescription = "some useful description",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayImages() {
    AvifImageFormatTestTheme {
    }
}