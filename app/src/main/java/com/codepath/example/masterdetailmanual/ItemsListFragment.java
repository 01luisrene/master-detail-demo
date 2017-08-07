package com.codepath.example.masterdetailmanual;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.example.masterdetailmanual.adaptadores.Lista;
import com.codepath.example.masterdetailmanual.modelos.Item;

public class ItemsListFragment extends Fragment {
    //Poblar una lista
    private RecyclerView mRecyclerView;
    private List<Item> mItems;
    private SQLiteDB mManager;


    //Variables para la comunicación entre fragment y Activity
	private OnItemSelectedListener listener;
	Activity activity;

	public interface OnItemSelectedListener {
		public void onItemSelected(Item i);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		//[Establecer comunicación entre nuestra lista y nuestro detalle]
		if (context instanceof Activity) {
			this.activity = (Activity) context;
			listener = (OnItemSelectedListener) this.activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement ItemsListFragment.OnItemSelectedListener");
		}

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Crear arraylist de accesorios de artículo
        //mItems = Item.getItems();
        //Item item = getArguments().getBinder("item");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate view
		View v = inflater.inflate(R.layout.fragment_items_list, container,
				false);
		// Bind adapter to ListView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.lista);

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mManager = new SQLiteDB(getActivity());

        mItems = mManager.getPostsList();

        Lista mListaAdapter = new Lista(mItems, getContext(), new OnItemSelectedListener() {
            @Override
            public void onItemSelected(Item i) {
                listener.onItemSelected(i);
                Toast.makeText(activity, "Click desde el fragment ItemsListFragment", Toast.LENGTH_SHORT).show();
            }
        });

        /*
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View item, int position,
					long rowId) {
				// Retrieve item based on position
				Item i = adapterItems.getItem(position);
				// Fire selected event for item
				listener.onItemSelected(i);
			}
		});
        */
        mRecyclerView.setAdapter(mListaAdapter);


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		return v;
	}
	
	/**
	 * Función para resaltar el item seleccionado de la lista
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		//lvItems.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
	}
}
