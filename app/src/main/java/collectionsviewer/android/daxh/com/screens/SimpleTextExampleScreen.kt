package collectionsviewer.android.daxh.com.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import collectionsviewer.android.daxh.com.R
import iterro.insurevault.custom.collectionsviewer.CollectionsViewer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.view_holder_simpletext.*

class SimpleTextExampleScreen : BaseScreen() {

    companion object {
        fun create(context: Context): Intent {
            return Intent(context, SimpleTextExampleScreen::class.java)
        }
    }

    private var collectionsViewer: CollectionsViewer<SimpleTextItem, SimpleTextViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_simpletextexample)

        val items = arrayListOf(
                SimpleTextItem("01 Text"),
                SimpleTextItem("02 Text text"),
                SimpleTextItem("03 Text text text"),
                SimpleTextItem("04 Text text text text"),
                SimpleTextItem("05 Text text text text text"),
                SimpleTextItem("06 Text text text text text text"),
                SimpleTextItem("07 Text text text text text text text"),
                SimpleTextItem("08 Text text text text text text text text"),
                SimpleTextItem("09 Text text text text text text text text text"),
                SimpleTextItem("10 Text text text text text text text text text text"),
                SimpleTextItem("11 Text text text text text text text text text text text"),
                SimpleTextItem("12 Text text text text text text text text text text text text"),
                SimpleTextItem("13 Text text text text text text text text text text text text text"),
                SimpleTextItem("14 Text text text text text text text text text text text text text text"),
                SimpleTextItem("15 Text text text text text text text text text text text text text text text"),
                SimpleTextItem("16 Text text text text text text text text text text text text text text text text"),
                SimpleTextItem("17 Text text text text text text text text text text text text text text text text text"),
                SimpleTextItem("18 Text text text text text text text text text text text text text text text text text text"),
                SimpleTextItem("19 Text text text text text text text text text text text text text text text text text text text")
        )

        collectionsViewer = CollectionsViewer.create<SimpleTextItem, SimpleTextViewHolder>(items, this)
                .configureCollectionsViewer {
//                    it.recyclerView.background =
                }
                .viewHolderCreate { collectionsViewer, parent, _ ->
                    val itemView = LayoutInflater.from(parent?.context)
                            .inflate(R.layout.view_holder_simpletext, parent, false)

                    // TODO This commented out code should be removed here and demonstrated as a separate example
                    // work here if you need to control height of your items
                    // keep in mind that parent is RecyclerView in this case
                    // val height: Double = ((parent?.measuredWidth ?: 0) / collectionsViewer.columnsNumCallback.invoke()) * 0.75
                    // itemView.minimumHeight = height.toInt()

                    val vh = SimpleTextViewHolder(itemView)
                    return@viewHolderCreate vh
                }.viewHolderBind { collectionsViewer, holder, position ->
                    holder?.item = collectionsViewer.data?.get(position)
                }.columnsNum {
                        return@columnsNum if (resources.configuration.orientation
                                == Configuration.ORIENTATION_PORTRAIT) { 1 } else { 2 }
                }
                .show(R.id.rlRoot, this)

    }
}

@SuppressLint("ParcelCreator")
@Parcelize
data class SimpleTextItem(
        val text: String = "") : Parcelable

class SimpleTextViewHolder(override val containerView: View?): RecyclerView.ViewHolder(containerView), LayoutContainer {

    var item: SimpleTextItem? = null
        set(value) {
            field = value
            tvDetails.text = value?.text
        }
}