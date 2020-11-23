#@ ImagePlus(label="Image") imageArg
#@ ImagePlus(label="Deformation field") dfieldArg


import java.util.function.*;

import ij.*;
import ij.gui.*;
import ij.process.*;

import net.imglib2.*;
import net.imglib2.view.*;
import net.imglib2.realtransform.*;
import net.imglib2.type.numeric.*;
import net.imglib2.img.imageplus.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.realtransform.*;
import net.imglib2.realtransform.inverse.*;
import net.imglib2.loops.*;
import net.imglib2.util.*;

/**
 * This script transforms the current image and any ROIs on the image.
 * 
 * Try placing different kinds of ROIs, and 
 * using a different kinds of transformations.
 */

dfieldIn = ImageJFunctions.wrap( dfieldArg );
dfield = new DeformationFieldTransform( 
	interpolateBorder( Views.hyperSlice( dfieldIn, 2, 0 )),
	interpolateBorder( Views.hyperSlice( dfieldIn, 2, 1 )));

// this will be a lot slower than the forward
transformation = new WrappedIterativeInvertibleRealTransform( dfield ).inverse();

image = ImageJFunctions.wrap( imageArg );



// get the roi / set of points
roi = imageArg.getRoi();

if( roi != null )
{
	// transform the image
	ip = transformAndShow( image, transformation, "transformed" );

	// transform the points
	roiTransformed = transformPointPolygon( roi, transformation );
	
	// display transformed points on the transformed image
	ip.setRoi( roiTransformed );
}
else
{
	IJ.showMessage("asdf", "Add an roi to the current image!");
}





/******************************
 *	ONLY FUNCTIONS BELOW HERE *
 ******************************/
 
def transformPointPolygon( roi, transform )
{
	// convert the roi to a densely spaced polygon if necessary	
	if( roi.getType() == Roi.POINT ) {
		polygon = roi.getFloatPolygon()
	}
	else{
		polygon = roi.getInterpolatedPolygon()
	}

	Npts = polygon.npoints;
	xpts = polygon.xpoints;
	ypts = polygon.ypoints;

	xpointsWarped = new float[ Npts ];
	ypointsWarped = new float[ Npts ];

	q = new RealPoint( 2 );
	for ( int i = 0; i < Npts; i++ )
	{
		p = new RealPoint( xpts[ i ], ypts[ i ] );
		transform.apply( p, q );
		xpointsWarped[ i ] = q.getDoublePosition( 0 );
		ypointsWarped[ i ] = q.getDoublePosition( 1 );
	}

	if( roi.getType() == Roi.POINT )
	{
		roiWarped = new PointRoi(  xpointsWarped, ypointsWarped );
		roiWarped.setSize( roi.getSize() );
		roiWarped.setPointType( roi.getPointType() );
		roiWarped.setPointType( roi.getPointType() );
		return roiWarped;
	}
	else
	{
		return new PolygonRoi( new FloatPolygon( xpointsWarped, ypointsWarped ), Roi.POLYGON );
	}
}

def transform( img, xfm )
{
	return RealViews.transform(
		Views.interpolate( 
			Views.extendZero( img ), 
			new NLinearInterpolatorFactory() ),
		xfm );
}

def transformToImage( img, xfm )
{
	return Views.interval( Views.raster( transform( img, xfm )), img );
}

def copy( img )
{
	result = new ImagePlusImgFactory( Util.getTypeFromInterval( img )).create( img );
	LoopBuilder.setImages( img, result ).forEachPixel({ x,y -> y.set(x.get()) } as BiConsumer );
	return result;
}

def transformAndShow( img, transform, name )
{
	ip = copy( transformToImage( img, transform )).getImagePlus();
	ip.setTitle( name );
	ip.show();
	return ip;
}

def interpolateBorder( img )
{
	return Views.interpolate( 
		Views.extendBorder( img ), new NLinearInterpolatorFactory() );
}

