package collectionsviewer.android.daxh.com.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import collectionsviewer.android.daxh.com.R
import collectionsviewer.android.daxh.com.collectionsviewer.CollectionsViewer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.collectionsviewer.*
import kotlinx.android.synthetic.main.view_holder_simpletext.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SimpleTextExampleScreen : BaseScreen() {

    companion object {
        fun create(context: Context): Intent {
            return Intent(context, SimpleTextExampleScreen::class.java)
        }
    }

    private val len = 7
    private val allData = arrayListOf(
            SimpleTextItem("01 text"),
            SimpleTextItem("02 text text"),
            SimpleTextItem("03 text text text "),
            SimpleTextItem("04 text text text text"),
            SimpleTextItem("05 text text text text text"),
            SimpleTextItem("06 text text text text text text"),
            SimpleTextItem("07 text text text text text text text"),
            SimpleTextItem("08 text text text text text text text text"),
            SimpleTextItem("09 text text text text text text text text text"),
            SimpleTextItem("10 text text text text text text text text text text"),
            SimpleTextItem("11 text text text text text text text text text text text"),
            SimpleTextItem("12 text text text text text text text text text text text text"),
            SimpleTextItem("13 text text text text text text text text text text text text text"),
            SimpleTextItem("14 text text text text text text text text text text text text text text"),
            SimpleTextItem("15 text text text text text text text text text text text text text text text"),
            SimpleTextItem("16 text text text text text text text text text text text text text text text text"),
            SimpleTextItem("17 text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("18 text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("19 text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("20 text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("21 text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("22 text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("23 text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("24 text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("25 text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("26 text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("27 text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("28 text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("29 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("30 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("31 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("32 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("33 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("34 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("35 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("36 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("37 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("38 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("39 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("40 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("41 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("42 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("43 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("44 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("45 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("46 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("47 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text"),
            SimpleTextItem("48 text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text")
    )

    private var collectionsViewer: CollectionsViewer<SimpleTextItem, SimpleTextViewHolder>? = null
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_simpletextexample)

        collectionsViewer = CollectionsViewer.create<SimpleTextItem, SimpleTextViewHolder>(ArrayList(allData.slice(0 until len)), this)
                .configureCollectionsViewer {
                    it.recyclerView.setBackgroundColor(Color.CYAN)
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
                }.viewHolderClicked { collectionsViewer, holder, position ->
                    startActivity(ItemDetailsScreen.create(this, holder?.item?.text))
                }.enablePullToRefresh { collectionsViewer ->
                    executor.execute {
                        Thread.sleep(3000)
                        collectionsViewer.setData(allData) {
                            collectionsViewer.stopPullToRefresh()
                        }
                    }
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

class SimpleTextViewHolder(override val containerView: View?): CollectionsViewer.ClickableViewHolder(containerView), LayoutContainer {
    var item: SimpleTextItem? = null
        set(value) {
            field = value
            tvDetails.text = value?.text
        }
}