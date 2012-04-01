package com.speedacm.treeview.data.storage;

import com.speedacm.treeview.models.Building;
import com.speedacm.treeview.models.Species;
import com.speedacm.treeview.models.Tree;
import com.speedacm.treeview.models.Zone;

public abstract class AbstractStorage
{
	protected AbstractStorage mFallback;
	
	public AbstractStorage(AbstractStorage fallback)
	{
		mFallback = fallback;
	}
	
	public abstract Zone[] getAllZones();
	public abstract void getZoneDetails(Zone target);
	public abstract Tree getTree(int id);
	public abstract Building[] getAllBuildings();
	public abstract Species[] getAllSpecies();
	public abstract Species getSpecies(int id);
}
