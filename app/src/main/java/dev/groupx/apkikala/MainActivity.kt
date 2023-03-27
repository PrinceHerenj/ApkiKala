package dev.groupx.apkikala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.groupx.apkikala.ui.theme.ApkiKalaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApkiKalaTheme {
                ApkiKalaApp()
            }
        }
    }
}
