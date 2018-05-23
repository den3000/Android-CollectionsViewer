package collectionsviewer.android.daxh.com.collectionsviewer

import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import collectionsviewer.android.daxh.com.R
import kotlinx.android.synthetic.main.collectionsviewer.*

class CollectionsViewer<I : Parcelable, H : RecyclerView.ViewHolder> : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val TAG = "CollectionsViewerFragmentTag"
        private const val EXTRA_DATA = "extra_data"
        private const val EXTRA_ISPULLTOREFRESH = "extra_ispulltorefresh"

        fun <I : Parcelable, H : RecyclerView.ViewHolder> create(forData: ArrayList<I>, inActivity: AppCompatActivity, withTag: String = TAG, forceData: Boolean = false): CollectionsViewer<I, H> {
            val fragmentManager = inActivity.supportFragmentManager
            var collectionsViewer : CollectionsViewer<I, H>? = fragmentManager.findFragmentByTag(withTag) as? CollectionsViewer<I, H>
            if (collectionsViewer == null) {
                collectionsViewer = CollectionsViewer()
                if (!forceData){
                    collectionsViewer.setData(forData)
                }
            }
            if (forceData){
                collectionsViewer.setData(forData)
            }
            return collectionsViewer
        }

        // Call this from Activity's onPause
        fun cleanUp(inActivity: AppCompatActivity, withTag: String = TAG) {
            if (inActivity.isFinishing) {
                val fm = inActivity.fragmentManager
                // we will not need this fragment anymore, this may also be a good place to signal
                // to the retained fragment object to perform its own cleanup.
                val collectionsViewer = fm!!.findFragmentByTag(withTag)
                fm.beginTransaction().remove(collectionsViewer).commit()
            }
        }
    }

    var data: ArrayList<I>? = null
        private set
    var columnsNumCallback = { 1 }
        private set
    var createViewHolderCallback: ((collectionsViewer: CollectionsViewer<I, H>, parent: ViewGroup?, viewType: Int) -> H)? = null
        private set
    var bindViewHolderCallback: ((collectionsViewer: CollectionsViewer<I, H>, holder: H?, position: Int) -> Unit)? = null
        private set
    var clickViewHolderCallback: ((collectionsViewer: CollectionsViewer<I, H>, holder: H?, position: Int) -> Unit)? = null
        private set

    var configureCollectionsViewerCallback: ((collectionsViewer: CollectionsViewer<I, H>) -> Unit)? = null
        private set
    var refreshLayoutCallback: ((collectionsViewer: CollectionsViewer<I, H>) -> Unit)? = null
        private set

    private var layoutManager: RecyclerView.LayoutManager? = null

    private var handler: Handler? = null

    fun show(inResId: Int, ofActivity: AppCompatActivity, withTag: String = TAG): CollectionsViewer<I, H> {
        val fragmentManager = ofActivity.supportFragmentManager
//        if (!isAdded) {
        if (fragmentManager.findFragmentByTag(withTag) == null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(inResId, this, withTag)
            fragmentTransaction.commit()
        }
        return this
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_ISPULLTOREFRESH, refreshLayout?.isRefreshing ?: false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        data = arguments?.getParcelableArrayList(EXTRA_DATA)
        return inflater.inflate(R.layout.collectionsviewer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        handler = Handler()

        layoutManager = StaggeredGridLayoutManager(columnsNumCallback(),1)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CollectionsViewerAdapter(this)

        refreshLayout.setOnRefreshListener(this)
        refreshLayout.isEnabled = refreshLayoutCallback != null
        savedInstanceState?.run {
            refreshLayout?.isRefreshing = getBoolean(EXTRA_ISPULLTOREFRESH)
        }

        configureCollectionsViewerCallback?.invoke(this)
    }

    fun setData(data: ArrayList<I>, callback: (() -> Unit)? = null) {
        this.data = data

        val bundle = Bundle()
        bundle.putParcelableArrayList(EXTRA_DATA, this.data)
        arguments = bundle

        handler?.post {
            recyclerView?.adapter?.notifyDataSetChanged()

            callback?.invoke()
        }
    }

    fun columnsNum(callback: (() -> Int)): CollectionsViewer<I, H> {
        this.columnsNumCallback = callback
        if (layoutManager != null) {
            layoutManager = GridLayoutManager(context, columnsNumCallback.invoke())
            recyclerView?.layoutManager = layoutManager
        }
        return this
    }

    fun viewHolderCreate(callback: (collectionsViewer: CollectionsViewer<I, H>, parent: ViewGroup?, viewType: Int) -> H): CollectionsViewer<I, H> {
        this.createViewHolderCallback = callback
        return this
    }

    fun viewHolderBind(callback: (collectionsViewer: CollectionsViewer<I, H>, holder: H?, position: Int) -> Unit): CollectionsViewer<I, H> {
        this.bindViewHolderCallback = callback
        return this
    }

    fun viewHolderClicked(callback: (collectionsViewer: CollectionsViewer<I, H>, holder: H?, position: Int) -> Unit): CollectionsViewer<I, H> {
        this.clickViewHolderCallback = callback
        return this
    }

    fun configureCollectionsViewer(callback: (collectionsViewer: CollectionsViewer<I, H>) -> Unit) : CollectionsViewer<I, H> {
        this.configureCollectionsViewerCallback = callback
        return this
    }

    fun enablePullToRefresh(callback: (collectionsViewer: CollectionsViewer<I, H>) -> Unit): CollectionsViewer<I, H> {
        this.refreshLayoutCallback = callback
        refreshLayout?.isEnabled = true
        return this
    }

    fun startPullToRefresh() {
        refreshLayout?.isRefreshing = true
    }

    fun stopPullToRefresh() {
        refreshLayout?.isRefreshing = false
    }

    override fun onRefresh() {
        refreshLayoutCallback?.invoke(this)
    }

    open class ClickableViewHolder(containerView: View?) : RecyclerView.ViewHolder(containerView), View.OnClickListener {
        var clickCallback: (() -> Unit)? = null

        init {
            containerView?.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickCallback?.invoke()
        }
    }
}

