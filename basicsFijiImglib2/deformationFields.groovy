#@ ImagePlus(label="Image") imageArg
#@ ImagePlus(label="Deformation field") dfieldArg


import bdv.util.*;
import net.imglib2.realtransform.*;
import net.imglib2.type.numeric.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.view.*;

image = ImageJFunctions.wrap( imageArg );
dfieldIn = ImageJFunctions.wrap( dfieldArg );


dfield = new DeformationFieldTransform( 
	interpolateBorder( Views.hyperSlice( dfieldIn, 2, 0 )),
	interpolateBorder( Views.hyperSlice( dfieldIn, 2, 1 )));

transformedImage = new RealTransformRandomAccessible( interpolateZero(image), dfield );

opts = BdvOptions.options();
if( image.numDimensions() == 2 )
	opts = opts.is2D();
	
bdv = BdvFunctions.show( image, "original", opts );
BdvFunctions.show( transformedImage, image, "transformed", opts.addTo( bdv ) );



/******************************
 *	ONLY FUNCTIONS BELOW HERE *
 ******************************/

def interpolateZero( img )
{
	return Views.interpolate( 
		Views.extendZero( img ), new NLinearInterpolatorFactory() );
}

def interpolateBorder( img )
{
	return Views.interpolate( 
		Views.extendBorder( img ), new NLinearInterpolatorFactory() );
}