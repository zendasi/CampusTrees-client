package com.speedacm.treeview.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.speedacm.treeview.R;
import com.speedacm.treeview.mapmodes.MapMode;
import com.speedacm.treeview.mapmodes.SustainMode;
import com.speedacm.treeview.mapmodes.TreeMode;

public class DynamicMapActivity extends MapActivity
{	
	private MapView mMap;
	private MapMode mMapMode;
	
	public static final int TREE_MODE = 0;
	public static final int SUSTAIN_MODE = 1;
	
	// latitude and longitude of "University of Louisville"
	// according to google: 38.2159018, -85.7581278
	
	private final GeoPoint mUofL_LatLong = new GeoPoint(38215901, -85758128);
	private final int mUofL_DefZoom = 18;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treemap);
		mMap = (MapView)findViewById(R.id.mainTreeMap);
		resetMap(false); // don't animate to point
		mMap.setBuiltInZoomControls(true);
		setMapMode(getIntent().getExtras().getInt("mapmode"));
	}
	
	private void setMapMode(int type)
	{
		if(mMapMode != null)
			mMapMode.onDeactivate();
		
		switch(type)
		{
		case TREE_MODE:
			setTitle(R.string.treemap_label);
			mMapMode = new TreeMode(this);
			break;
			
		case SUSTAIN_MODE:
			setTitle(R.string.sustmap_label);
			mMapMode = new SustainMode(this);
			break;
		}
		
		mMapMode.onActivate();
	}
	
	public void resetMap(boolean animate)
	{
		MapController mc = mMap.getController();
		mc.setZoom(mUofL_DefZoom);
		if(animate)
			mc.animateTo(mUofL_LatLong);
		else
			mc.setCenter(mUofL_LatLong);
	}
	
	/** This function is only called once, before the menu is shown */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mapmenu, menu);
		return true;
	}
	
	/** This function is called before the menu is opened every time */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		return true;
	}
	
	/** This function is how we hook into the options handlers */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: tie this into the MapMode with dynamic menus
		switch(item.getItemId())
		{
			case R.id.mapm_filtertrees:
				break;
			case R.id.mapm_treetypes:
				break;
			case R.id.mapm_resetview:
				resetMap(true); // animate to reset point
				break;
		}
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
