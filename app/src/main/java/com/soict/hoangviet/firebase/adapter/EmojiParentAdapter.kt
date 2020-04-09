package com.soict.hoangviet.firebase.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.soict.hoangviet.baseproject.extension.inflate
import com.soict.hoangviet.firebase.R
import com.soict.hoangviet.firebase.data.local.Emoji
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_emoji_parent.*

class EmojiParentAdapter(context: Context) : EndlessLoadingRecyclerViewAdapter(context) {

    override fun initNormalViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder? {
        return MultipleViewHolder(parent.inflate(R.layout.item_emoji_parent))
    }

    override fun bindNormalViewHolder(holder: NormalViewHolder, position: Int) {
        holder.bind(getItemPosition(position, Emoji::class.java))
    }

    class MultipleViewHolder(override val containerView: View?) : NormalViewHolder(containerView!!),
        LayoutContainer {
        override fun <T> bind(data: T) {
            data as Emoji
            val mEmojiChildAdapter = EmojiChildAdapter(itemView.context, data.emojiName)
            rcv_view_pager_emoji.adapter = mEmojiChildAdapter
            rcv_view_pager_emoji.layoutManager = GridLayoutManager(itemView.context, 4)
            mEmojiChildAdapter.addModels(data.listImagesEmoji, false)
        }
    }
}