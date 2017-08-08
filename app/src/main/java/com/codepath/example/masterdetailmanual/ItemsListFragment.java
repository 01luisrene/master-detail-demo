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

	//Interface asociada a acitividad padre
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

	//Función llamada cuando el fragment es desasociada de una actividad
	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null) {
			// Manejo de argumentos
		}
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
            public void onItemSelected(Item item) {

                listener.onItemSelected(item);

            }
        });

        mRecyclerView.setAdapter(mListaAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		return v;
	}

	/**
	 * Función para resaltar el item seleccionado de la lista
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {

       // mRecyclerView.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
	}
}
