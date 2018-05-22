package collectionsviewer.android.daxh.com.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import collectionsviewer.android.daxh.com.R
import kotlinx.android.synthetic.main.screen_itemdetails.*

class ItemDetailsScreen : BaseScreen() {

    companion object {
        private const val EXTRA_TEXT = "extra_policyitem"

        fun create(context: Context, text: String?): Intent {
            val i = Intent(context, ItemDetailsScreen::class.java)
            if (text != null) {
                i.putExtra(EXTRA_TEXT, text)
            }
            return i
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_itemdetails)

        tvDetails.text = intent.getStringExtra(EXTRA_TEXT)
    }
}
