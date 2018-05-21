package collectionsviewer.android.daxh.com.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import collectionsviewer.android.daxh.com.R

class SimpleTextExampleScreen : BaseScreen() {

    companion object {
        fun create(context: Context): Intent {
            return Intent(context, SimpleTextExampleScreen::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_simpletextexample)
    }
}