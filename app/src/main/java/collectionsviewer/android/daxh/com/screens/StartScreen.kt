package collectionsviewer.android.daxh.com.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import collectionsviewer.android.daxh.com.R
import collectionsviewer.android.daxh.com.custom.toast
import kotlinx.android.synthetic.main.screen_start.*

class StartScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_start)

        btSimpleTextExample?.setOnClickListener { toast(R.string.btSimpleTextExample) }
    }
}
