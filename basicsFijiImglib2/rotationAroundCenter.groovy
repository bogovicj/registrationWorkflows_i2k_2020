#@ ImagePlus(label="Image") imageArg
#@ Double (labels="rotation (deg)") rotationDegrees


import bdv.util.*;
import net.imglib2.realtransform.*;
import net.imglib2.type.numeric.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.view.*;

image = ImageJFunctions.wrap( imageArg );

def interpolate( img )
{
	return Views.interpolate( 
		Views.extendZero( img ), new NLinearInterpolatorFactory() );
}

def centeringTransform( img )
{
	cx = img.realMax( 0 ) / 2; // center of y
	cy = img.realMax( 1 ) / 2; // center of y
	return new Translation2D( -cx, -cy );
}

totalTransform = new AffineTransform2D();
totalTransform.rotate( Math.toRadians( rotationDegrees ));

c = centeringTransform( image );
totalTransform.concatenate( c )
totalTransform.preConcatenate( c.inverse() );

transformedImage = RealViews.affine( 
		interpolate( image ),
		totalTransform );



opts = BdvOptions.options();
if( image.numDimensions() == 2 )
	opts = opts.is2D();
	
bdv = BdvFunctions.show( image, "original", opts );
BdvFunctions.show( transformedImage, image, "transformed", opts.addTo( bdv ) );


