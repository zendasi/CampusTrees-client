package com.speedacm.treeview.mapitems;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.speedacm.treeview.models.Zone;

public class ZoneItem extends Overlay
{
	
	private Zone mZone;
	
	public ZoneItem(Zone zone)
	{
		mZone = zone;
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		// TODO: compare the point to see if it falls within the polygon
		return false;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		
		if(shadow) return;
		
		// TODO: don't draw this if everything is outside the view
		//       this may require a bounding box on the Zone
		Projection prj = mapView.getProjection();
		boolean firstPoint = true;
		Point orig = null;
		
		Path path = new Path();
		for(GeoPoint gp : mZone.getPoints())
		{
			Point p = new Point();
			
			prj.toPixels(gp, p); // convert latlong to screen coords
			
			if(firstPoint)
			{
				orig = p;
				path.moveTo(p.x, p.y);
				firstPoint = false;
			}
			else
			{
				path.lineTo(p.x, p.y);
			}
			
		}
		
		// complete the path's loop around
		path.lineTo(orig.x, orig.y);
		
		// now that we have the path generated, go ahead and draw it
		Paint paint = new Paint();
		
		// draw the filled area
		paint.setStyle(Style.FILL);
		paint.setColor(Color.CYAN);
		paint.setAlpha(127);
		canvas.drawPath(path, paint);
		
		// draw the stroke outline
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		canvas.drawPath(path, paint);
	}
}
