#@ ImagePlus(label="Image") imageArg
#@ Double (labels="Total rotation (deg)") rotationDegrees
#@ Integer (labels="Number of steps") numSteps
#@ Boolean (labels="Concatenate transforms") concatTransforms

import java.util.function.*;

import ij.*;
import net.imglib2.view.*;
import net.imglib2.realtransform.*;
import net.imglib2.type.numeric.*;
import net.imglib2.img.imageplus.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.loops.*;
import net.imglib2.util.*;



def transform( img, affine )
{
	return RealViews.affine(
		Views.interpolate( 
			Views.extendZero( img ), 
			new NLinearInterpolatorFactory() ),
		affine );
}

def transformToImage( img, affine )
{
	return Views.interval( Views.raster( transform( img, affine )), img );
}

def copy( img )
{
	result = new ImagePlusImgFactory( Util.getTypeFromInterval( img )).create( img );
	LoopBuilder.setImages( img, result ).forEachPixel({ x,y -> y.set(x.get()) } as BiConsumer );
	return result;
}

def transformToCopy( img, affine )
{
	return copy( transformToImage( img, affine ));
}

def rotationAroundCenter( img, rotationDegrees )
{
	cx = img.realMax( 0 ) / 2; // center of y
	cy = img.realMax( 1 ) / 2; // center of y
	c = new Translation2D( -cx, -cy );
	
	totalTransform = new AffineTransform2D();
	totalTransform.rotate( Math.toRadians( rotationDegrees ));
	totalTransform.concatenate( c )
	totalTransform.preConcatenate( c.inverse() );
	return totalTransform;
}


image = ImageJFunctions.wrap( imageArg );
affine = rotationAroundCenter( image, rotationDegrees );
partialAffine = rotationAroundCenter( image, rotationDegrees / numSteps );

imageStack = new ImageStack();
imageStack.addSlice( imageArg.getProcessor() )

if ( concatTransforms )
{
	totalAffine = new AffineTransform2D();
	
	for ( i in 1..numSteps)
	{
		println( i );
		// transform the original image by the full amount
		totalAffine.concatenate( partialAffine );
		currentImage = transformToCopy( image, totalAffine );
		imageStack.addSlice( currentImage.getImagePlus().getProcessor() );
	}
	name = "transform concatenate affines";
}
else
{
	currentImage = image;
	for ( i in 1..numSteps)
	{
		println( i );
		// transform the last image by the step
		currentImage = transformToCopy( currentImage, partialAffine );
		imageStack.addSlice( currentImage.getImagePlus().getProcessor() );
	}
	name = "transform";
}


resultImage = new ImagePlus( name, imageStack );
//resultImage.setDimensions( 1, 1, numSteps );
resultImage.show();


