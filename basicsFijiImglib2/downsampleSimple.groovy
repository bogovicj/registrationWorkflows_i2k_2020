#@ ImagePlus (label="downsample factor") imageArg
#@ Integer (label="downsample factor") downsampleFactor

import java.util.function.*;

import ij.*;
import ij.gui.*;
import ij.process.*;

import net.imglib2.*;
import net.imglib2.view.*;
import net.imglib2.realtransform.*;
import net.imglib2.type.numeric.*;
import net.imglib2.type.numeric.real.*;
import net.imglib2.img.imageplus.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.position.*;
import net.imglib2.loops.*;
import net.imglib2.util.*;

img = ImageJFunctions.wrap( imageArg );

factor = 1.0 / ((double)downsampleFactor)

resampledInterval = new FinalInterval( 
	Intervals.dimensionsAsLongArray( img ).collect{ Math.ceil( it * factor)} as long[]);

println( resampledInterval );

long[] subfactors;
if( img.numDimensions() == 2 ){
	subfactors = [ downsampleFactor, downsampleFactor ] as long[]
}
else if( img.numDimensions() == 3 ){
	subfactors = [ downsampleFactor, downsampleFactor, downsampleFactor ] as long[]	
}

subImg = Views.subsample( img, subfactors );
ip = toImagePlus( imageArg.getTitle() + "_downsampled", subImg );

ip.getCalibration().pixelWidth = imageArg.getCalibration().pixelWidth * downsampleFactor;
ip.getCalibration().pixelHeight = imageArg.getCalibration().pixelHeight * downsampleFactor;
if( img.numDimensions() == 3 ){
	ip.getCalibration().pixelDepth = imageArg.getCalibration().pixelDepth * downsampleFactor;	
}
ip.show();

def toImagePlus( name, img )
{
	result = new ImagePlusImgFactory( Util.getTypeFromInterval( img )).create( img );
	LoopBuilder.setImages( img, result ).forEachPixel({ x,y -> y.set(x.get()) } as BiConsumer );
	ip = result.getImagePlus();
	ip.setDimensions( 1, 2, 1 );
	ip.setTitle( name );
	return ip;
}

