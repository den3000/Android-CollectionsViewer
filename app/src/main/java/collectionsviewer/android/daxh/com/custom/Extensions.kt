package collectionsviewer.android.daxh.com.custom

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast

inline fun <T: View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    if (message.isEmpty() || message.trim() == "") return
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(msgRestId: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, resources.getString(msgRestId), duration).show()
}