#@ ImagePlus(label="resize this image") resizeThisImage
#@ ImagePlus(label="target image") targetImage

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
import net.imglib2.loops.*;
import net.imglib2.util.*;

resizeImg = ImageJFunctions.wrap( resizeThisImage );
targetImg = ImageJFunctions.wrap( targetImage );

nd = resizeImg.numDimensions();

transform = new AffineTransform2D();

/*
 * set the transform here
 * 
 * some helpful methods:
 * 	 transform.scale( scalex, scaley );
 * 	 transform.set( a, b, c, d, e, f );
 * 	 
 * 	 resizeImg.dimension( 0 ) // the width of resizeImg
 * 	 resizeImg.dimension( 1 ) // the height of resizeImg
 */ 


// you don't have to modify anything below this
transformAndShow( resizeImg, targetImg, transform, "resized" );


/******************************
 *	ONLY FUNCTIONS BELOW HERE *
 ******************************/

def transform( img, affine )
{
	return RealViews.affine(
		Views.interpolate( 
			Views.extendZero( img ), 
			new NLinearInterpolatorFactory() ),
		affine );
}

def transformToImage( img, targetImg, affine )
{
	return Views.interval( Views.raster( transform( img, affine )), targetImg );
}

def copy( img )
{
	result = new ImagePlusImgFactory( Util.getTypeFromInterval( img )).create( img );
	LoopBuilder.setImages( img, result ).forEachPixel({ x,y -> y.set(x.get()) } as BiConsumer );
	return result;
}

def transformAndShow( img, targetImg, transform, name )
{
	ip = copy( transformToImage( img, targetImg, transform )).getImagePlus();
	ip.setTitle( name );
	ip.show();
	return ip;
}
