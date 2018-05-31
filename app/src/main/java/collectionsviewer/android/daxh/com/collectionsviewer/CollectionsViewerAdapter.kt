package collectionsviewer.android.daxh.com.collectionsviewer

import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class CollectionsViewerAdapter<I : Parcelable, H : RecyclerView.ViewHolder>(val collectionsViewer: CollectionsViewer<I, H>): RecyclerView.Adapter<H>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        // TODO Potential issue
        return collectionsViewer.createViewHolderCallback!!.invoke(collectionsViewer, parent, viewType)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        collectionsViewer.bindViewHolderCallback?.invoke(collectionsViewer, holder, position)
        if (collectionsViewer.clickViewHolderCallback != null && holder is CollectionsViewer.ClickableViewHolder) {
            // TODO Better to test it with LeakCanary, to be sure that this is fine
            holder.clickCallback = {
                collectionsViewer.clickViewHolderCallback?.invoke(collectionsViewer, holder, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return collectionsViewer.data?.size ?: 0
    }
}

