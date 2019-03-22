package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.BuyerScreeningEntity;
import com.huxin.communication.entity.InlandCityEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.huxin.communication.controls.Constanst.CITY_CODE;
import static com.huxin.communication.controls.Constanst.CITY_MUDI_CODE;
import static com.huxin.communication.controls.Constanst.CITY_MUDI_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.CITY_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.PROVINCE_CODE;
import static com.huxin.communication.controls.Constanst.PROVINCE_MUDI_CODE;
import static com.huxin.communication.controls.Constanst.PROVINCE_MUDI_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.PROVINCE_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.TICKET_CITY_NAME;

public class CityTravelsAdapter extends RecyclerView.Adapter<CityTravelsAdapter.BodyViewHoder> {

    private Activity mActivity;
    private List<InlandCityEntity> mList;
    private LayoutInflater mInflater;
    private int type;

    private TableNameAdapter mAdapterTableName;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private Set<String> strings = new HashSet<>();

    private int mPosition = -1;

    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<InlandCityEntity> list) {
        this.mList = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<InlandCityEntity> getSelectedItem() {
        ArrayList<InlandCityEntity> selectList = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i));
                    strings.add(String.valueOf(mList.get(i).getCity_name()));
                } else {
                    strings.remove(String.valueOf(mList.get(i).getCity_name()));
                }
            }
        }
        return selectList;
    }

    public CityTravelsAdapter(List<InlandCityEntity> mList, Activity mActivity, int type) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.type = type;
        mInflater = LayoutInflater.from(mActivity);
        if (mList == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_travel_mudi_recycler, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    mPosition = Hoder.getAdapterPosition();
                    if (mList.get(Hoder.getAdapterPosition()).getCity_name().contains("市")) {
                        PreferenceUtil.putString(CITY_TRAVEL_NAME, mList.get(Hoder.getAdapterPosition()).getCity_name());
                    } else {
                        PreferenceUtil.putString(CITY_TRAVEL_NAME, mList.get(Hoder.getAdapterPosition()).getCity_name() + "市");
                    }
                    PreferenceUtil.putString(CITY_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_code()));
                    PreferenceUtil.putString(PROVINCE_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_code()));
                    PreferenceUtil.putString(PROVINCE_TRAVEL_NAME, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_name()));
                    notifyDataSetChanged();

                } else if (type == 3) {
                    if (isItemChecked(Hoder.getAdapterPosition())) {
                        setItemChecked(Hoder.getAdapterPosition(), false);
                    } else {
                        setItemChecked(Hoder.getAdapterPosition(), true);
                    }
                    notifyItemChanged(Hoder.getAdapterPosition());
                    getSelectedItem();

                    Iterator<String> iterator = strings.iterator();
                    String userStr = null;
                    while (iterator.hasNext()) {
                        userStr += iterator.next() + ",";
                    }
                    if (!TextUtils.isEmpty(userStr)) {
                        if (mList.get(Hoder.getAdapterPosition()).getCity_name().contains("市")) {
                            PreferenceUtil.putString(CITY_MUDI_TRAVEL_NAME, userStr.substring(4, userStr.length() - 1));
                        } else {
                            PreferenceUtil.putString(CITY_MUDI_TRAVEL_NAME, userStr.substring(4, userStr.length() - 1) + "市");
                        }
                    }
                    KyLog.d(String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_code()));
                    PreferenceUtil.putString(CITY_MUDI_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_code()));
                    PreferenceUtil.putString(PROVINCE_MUDI_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_code()));
                    PreferenceUtil.putString(PROVINCE_MUDI_TRAVEL_NAME, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_name()));

//                    Intent intent = new Intent(mActivity, ShuaiShuanInlandSpotActivity.class);
//                    mActivity.startActivity(intent);
                }
            }
        });
        return Hoder;
    }

    //设置给定位置条目的选择状态
    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //根据位置判断条目是否可选
    private boolean isSelectable() {
        return mIsSelectable;
    }

    //设置给定位置条目的可选与否的状态
    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
        KyLog.d(mList.get(position).getCity_name());
        holder.mTextViewName.setText(mList.get(position).getCity_name());

        if (type == 1) {
            holder.mTextViewmClicked.setVisibility(View.GONE);
            if (position == mPosition) {
                holder.mTextViewName.setTextColor(mActivity.getResources().getColor(R.color.blue));
            } else {
                holder.mTextViewName.setTextColor(mActivity.getResources().getColor(R.color.Font_color_title));

            }
        } else {
            holder.mTextViewmClicked.setVisibility(View.VISIBLE);
            if (isItemChecked(position)) {
                holder.mTextViewmClicked.setBackgroundResource(R.drawable.icon_circle_selected);

            } else {
                holder.mTextViewmClicked.setBackgroundResource(R.drawable.icon_circle_normal);

            }

        }


    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private RelativeLayout mRelativeLayout;
        private ImageView mTextViewmClicked;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.City_Name);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.mudi_line);
            mTextViewmClicked = itemView.findViewById(R.id.mudi_clicked);


        }
    }

    public void NotifyChanged() {
        notifyDataSetChanged();
    }
}
