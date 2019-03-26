package com.tencent.qcloud.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.uikit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupDeleteMemberAdapter extends RecyclerView.Adapter<GroupDeleteMemberAdapter.MemberViewHoder>{

    private Context mContext;
    private JSONArray data;
    private DeleteListener listener;
    private ArrayList<String> deleteUserId = new ArrayList<>();

    public GroupDeleteMemberAdapter(Context context, JSONArray jsonArray, DeleteListener listener) {
        this.mContext = context;
        this.data = jsonArray;
        this.listener = listener;
    }

    @Override
    public MemberViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MemberViewHoder(inflater.inflate(R.layout.group_delete_member, parent, false));
    }

    @Override
    public void onBindViewHolder(MemberViewHoder holder, int position) {
        try {
            JSONObject jsonObject = data.getJSONObject(position);
            String headUrl = jsonObject.getString("headUrl");
            String username = jsonObject.getString("username");
            String positions = jsonObject.getString("positions");
            String companyName = jsonObject.getString("companyName");
            String industryType = jsonObject.getString("industryType");
            Glide.with(mContext).load(headUrl).placeholder(R.drawable.default_head).into(holder.headUrl);
            if("".equalsIgnoreCase(industryType)) {
                holder.companyLl.setVisibility(View.VISIBLE);
                holder.companyTv.setText(companyName);
                if("".equalsIgnoreCase(username)) {
                    holder.nameAndPos.setText(positions);
                }else if("".equalsIgnoreCase(positions)) {
                    holder.nameAndPos.setText(username);
                }else {
                    holder.nameAndPos.setText(username + "•" + positions);
                }
            }else {
                holder.noCompanyLl.setVisibility(View.VISIBLE);
                if("".equalsIgnoreCase(username)) {
                    holder.nameIndusTv.setText(industryType);
                }else if("".equalsIgnoreCase(industryType)) {
                    holder.nameIndusTv.setText(username);
                }else {
                    holder.nameIndusTv.setText(username + "•" + industryType);
                }
            }
            setOnClickListener(holder, position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return null != data ? data.length() : 0;
    }

    private void setOnClickListener(MemberViewHoder holder, int position) {
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if(isChecked) {
                        deleteUserId.add(data.getJSONObject(position).getInt("uid") + "");
                        if(listener != null) {
                            listener.click(deleteUserId);
                        }
                    }else {
                        for(int i = 0; i < deleteUserId.size(); i++) {
                            if((data.getJSONObject(position).getInt("uid") + "").equalsIgnoreCase(new String(deleteUserId.get(i)))) {
                                deleteUserId.remove(i);
                            }
                        }
                        if(listener != null) {
                            listener.click(deleteUserId);
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public interface DeleteListener {
        void click(ArrayList<String> deleteUserId);
    }

    public class MemberViewHoder extends RecyclerView.ViewHolder {

        private LinearLayout itemLayout;
        private CheckBox checkbox;
        private ImageView headUrl;
        private LinearLayout companyLl;
        private TextView nameAndPos;
        private TextView companyTv;
        private LinearLayout noCompanyLl;
        private TextView nameIndusTv;

        public MemberViewHoder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item);
            checkbox = itemView.findViewById(R.id.checkbox);
            headUrl = itemView.findViewById(R.id.headUrl_iv);
            companyLl = itemView.findViewById(R.id.company);
            nameAndPos = itemView.findViewById(R.id.name_pos_tv);
            companyTv = itemView.findViewById(R.id.company_tv);
            noCompanyLl = itemView.findViewById(R.id.no_company);
            nameIndusTv = itemView.findViewById(R.id.name_indus_tv);
        }

    }
}
