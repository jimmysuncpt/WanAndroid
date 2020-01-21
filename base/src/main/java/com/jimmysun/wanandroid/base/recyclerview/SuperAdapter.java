package com.jimmysun.wanandroid.base.recyclerview;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * An adapter which supports paging and all kinds of data.
 *
 * @author SunQiang
 * @since 2020-01-16
 */
public class SuperAdapter extends RecyclerView.Adapter<SuperViewHolder> {
    private List<Object> mData = new ArrayList<>();
    private SparseArray<ViewHolderEntry> mHolderEntryMap = new SparseArray<>();

    private OnPageChangeListener mOnPageChangeListener;
    private int mStartPage;
    private int mNextPage;
    private int mLoadMoreOffset;
    private boolean mIsLoading;

    private SuperAdapter(List<ViewHolderEntry> holderEntryList,
                         OnPageChangeListener onPageChangeListener,
                         int startPage,
                         int loadMoreOffset) {
        if (!holderEntryList.isEmpty()) {
            try {
                for (ViewHolderEntry entry : holderEntryList) {
                    Class<? extends SuperViewHolder> holderClass = entry.getHolderClass();
                    // https://stackoverflow.com/q/3437897
                    String className = ((ParameterizedType) holderClass.getGenericSuperclass())
                            .getActualTypeArguments()[0].toString().split(" ")[1];
                    Class classOfData = Class.forName(className);
                    mHolderEntryMap.put(classOfData.hashCode(), entry);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mOnPageChangeListener = onPageChangeListener;
        mNextPage = mStartPage = startPage;
        mLoadMoreOffset = loadMoreOffset;
    }

    public List<Object> getData() {
        return mData;
    }

    public void init() {
        mNextPage = mStartPage;
        mData.clear();
        notifyDataSetChanged();
    }

    public void loadMore(List<Object> data) {
        mNextPage++;
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, mData.size() - startPos);
        mIsLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getClass().hashCode();
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolderEntry entry = mHolderEntryMap.get(viewType);
        if (entry == null) {
            throw new RuntimeException("no such view type: " + viewType);
        }
        Class<? extends SuperViewHolder> classOfViewHolder = entry.getHolderClass();
        Layout layout = classOfViewHolder.getAnnotation(Layout.class);
        if (layout == null) {
            throw new RuntimeException("you should add @Layout at your ViewHolder class " +
                    "definition");
        }
        View rootView = LayoutInflater.from(parent.getContext()).inflate(layout.value(), parent,
                false);
        try {
            SuperViewHolder holder =
                    classOfViewHolder.getDeclaredConstructor(View.class).newInstance(rootView);
            holder.onCreate(parent);
            if (entry.getOnCreateListener() != null) {
                entry.getOnCreateListener().onCreate(holder);
            }
            return holder;
        } catch (Exception e) {
            throw new RuntimeException("your ViewHolder should have a constructor with a View");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, int position) {
        Object data = mData.get(position);
        if (data == null) {
            return;
        }
        holder.onBind(data, position);
        int loadOffset = 0;
        if (mLoadMoreOffset > 0 && mLoadMoreOffset < mData.size()) {
            loadOffset = mLoadMoreOffset;
        }
        if (mOnPageChangeListener != null && !mIsLoading &&
                (position == getItemCount() - loadOffset - 1 || position == getItemCount() - 1)) {
            mIsLoading = true;
            mOnPageChangeListener.onLoadMore(mNextPage);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onViewRecycled(@NonNull SuperViewHolder holder) {
        holder.onViewRecycled();
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull SuperViewHolder holder) {
        return holder.onFailedToRecycleView();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SuperViewHolder holder) {
        holder.onViewAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SuperViewHolder holder) {
        holder.onViewDetachedFromWindow();
    }

    public static class Builder {
        private List<ViewHolderEntry> mEntryList = new ArrayList<>();
        private OnPageChangeListener mOnPageChangeListener;
        private int mStartPage;
        private int mLoadMoreOffset;

        /**
         * Register a ViewHolder class.
         *
         * @param classOfViewHolder The class of ViewHolder, which overrides SuperViewHolder.
         */
        public Builder registerViewHolder(@NonNull Class<? extends SuperViewHolder> classOfViewHolder) {
            mEntryList.add(new ViewHolderEntry(classOfViewHolder, null));
            return this;
        }

        public Builder registerViewHolder(@NonNull Class<? extends SuperViewHolder> classOfViewHolder
                , OnHolderCreateListener onHolderCreateListener) {
            mEntryList.add(new ViewHolderEntry(classOfViewHolder, onHolderCreateListener));
            return this;
        }

        public Builder setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
            mOnPageChangeListener = onPageChangeListener;
            return this;
        }

        public Builder setStartPage(int startPage) {
            mStartPage = startPage;
            return this;
        }

        public Builder setLoadMoreOffset(int loadMoreOffset) {
            mLoadMoreOffset = loadMoreOffset;
            return this;
        }

        public SuperAdapter build() {
            return new SuperAdapter(mEntryList, mOnPageChangeListener, mStartPage,
                    mLoadMoreOffset);
        }
    }
}
