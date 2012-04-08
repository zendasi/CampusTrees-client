package com.speedacm.treeview.models;

import com.google.android.maps.GeoPoint;
import com.speedacm.treeview.data.DataStore;

public class Tree
{
	private int mID;  // tree ID
	private int mSID; // species ID
	
	private float mDBH;
	private float mHeight;
	private float mGreenWt;
	private float mDryWt;
	private int   mAge;
	private float mCO2Seq;
	
	private GeoPoint mLatLong;
	
	public Tree(int id, int sid, GeoPoint point, float dbh, float height, float greenwt, float drywt, int age, float co2seq)
	{
		mID = id;
		mSID = sid;
		mLatLong = point;
		mDBH = dbh;
		mHeight = height;
		mGreenWt = greenwt;
		mDryWt = drywt;
		mAge = age;
		mCO2Seq = co2seq;
	}
	
	public int getID() { return mID; }
	public int getSpeciesID() { return mSID; }
	public GeoPoint getLocation() { return mLatLong; }
	public float getDBH() { return mDBH; }
	public float getHeight() { return mHeight; }
	public int getAge() { return mAge; }
	public float getCO2Seq() { return mCO2Seq; }
	public float getDryWeight() { return mDryWt; }
	public float getGreenWeight() { return mGreenWt; }
	public void setLocation(GeoPoint latLong) { mLatLong = latLong; }
	
	public Species getSpecies()
	{
		// call the synchronous function
		return DataStore.getInstance().getSpecies(mSID);
	}
}
