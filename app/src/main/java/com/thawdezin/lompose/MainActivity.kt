package com.thawdezin.lompose

import android.content.res.AssetManager
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.thawdezin.lompose.ui.theme.LomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val a = """
                    {"name":"HellO"}
                    """
                    HtmlView(jsonData = a)
                }
            }
        }
    }
}

@Composable
fun HtmlView(jsonData: String) {
    val assetManager = LocalContext.current.assets
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                loadDataWithBaseURL(
                    null,
                    loadHtmlFromAssets(assetManager, jsonData),
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        update = { view ->
            view.loadDataWithBaseURL(
                null,
                loadHtmlFromAssets(assetManager, jsonData),
                "text/html",
                "UTF-8",
                null
            )
        }
    )

}

fun loadHtmlFromAssets(assetManager: AssetManager, jsonData: String): String {
    val htmlTemplate = assetManager.open("index.html").bufferedReader().use { it.readText() }
    return htmlTemplate.replace("{{JSON_DATA}}", jsonData)
}
