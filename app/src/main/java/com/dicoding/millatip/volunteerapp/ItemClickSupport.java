package com.dicoding.millatip.volunteerapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemClickSupport {
    private RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private ItemClickSupport(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                onItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    private View.OnLongClickListener mOnLongClickLIstener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null){
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return onItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener mAttachListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (onItemClickListener != null){
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnLongClickLIstener != null){
                view.setOnLongClickListener(mOnLongClickLIstener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    public static ItemClickSupport addTo(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null){
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support != null){
            support.detach(view);
        }
        return support;
    }

    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
        return this;
    }

    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener){
        onItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view){
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    private interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    private interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
