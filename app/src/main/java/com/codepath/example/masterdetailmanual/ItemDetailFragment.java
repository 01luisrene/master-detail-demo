package com.codepath.example.masterdetailmanual;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.example.masterdetailmanual.modelos.Item;
import com.squareup.picasso.Picasso;

public class ItemDetailFragment extends Fragment {

	//Llave para usar con parcelable
	public static final String ID_RECORDATORIO = "idRecordatorio";
	private Item item;
	Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ID_RECORDATORIO)) {

			item = getArguments().getParcelable(ID_RECORDATORIO);

			activity = this.getActivity();
			CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            ImageView imagen = (ImageView) activity.findViewById(R.id.image_paralax);

			if (appBarLayout != null && imagen != null) {
				appBarLayout.setTitle(item.getTitle());
                Picasso.with(getContext()).load(R.drawable.ic_launcher).into(imagen);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_item_detail, 
				container, false);
		TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		TextView tvBody = (TextView) view.findViewById(R.id.tvBody);

		tvTitle.setText(item.getTitle());
		tvBody.setText(item.getBody());

		return view;
	}
    
    // ItemDetailFragment.newInstance(item)
    public static ItemDetailFragment newInstance(Item item) {
    	ItemDetailFragment fragmentDemo = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ID_RECORDATORIO, item);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}
