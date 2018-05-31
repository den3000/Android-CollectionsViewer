package collectionsviewer.android.daxh.com.screens

import android.os.Bundle
import collectionsviewer.android.daxh.com.R
import kotlinx.android.synthetic.main.screen_start.*

class StartScreen : BaseScreen() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_start)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        btSimpleTextExample.setOnClickListener { startActivity(SimpleTextExampleScreen.create(this)) }
    }
}
