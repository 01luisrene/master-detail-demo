package com.codepath.example.masterdetailmanual;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.FrameLayout;

import com.codepath.example.masterdetailmanual.ItemsListFragment.OnItemSelectedListener;
import com.codepath.example.masterdetailmanual.modelos.Item;

public class ItemsListActivity extends FragmentActivity implements OnItemSelectedListener {

	private boolean dosPaneles = false;

    SQLiteDB manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);

		determinePaneLayout();

        /*
        manager = new SQLiteDB(this);
        manager.addPost(null, "Titulo 6", "Lorem lorem ipsum");
        manager.addPost(null, "Titulo 7", "Lorem lorem ipsum");
        manager.addPost(null, "Titulo 8", "Lorem lorem ipsum");
        manager.addPost(null, "Titulo 9", "Lorem lorem ipsum");
        manager.addPost(null, "Titulo 10", "Lorem lorem ipsum");
        */

	}

	private void determinePaneLayout() {
		FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
		if (fragmentItemDetail != null) {
            dosPaneles = true;
			ItemsListFragment fragmentItemsList = (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);

            //Función que resalta el item seleccionado
			fragmentItemsList.setActivateOnItemClick(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.items, menu);
		return true;
	}

	@Override
	public void onItemSelected(Item item) {
		if (dosPaneles) { // Actividad única con lista y detalle

			// Reemplazar el diseño del marco con el fragmento de detalle correcto
			ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(item);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItem);
			ft.commit();

		} else { // Actividades separadas

			// Carga la actividad Detalle
			Intent intent = new Intent(this, ItemDetailActivity.class);
			intent.putExtra(ItemDetailFragment.ID_RECORDATORIO, item);
			startActivity(intent);

		}
	}

}
