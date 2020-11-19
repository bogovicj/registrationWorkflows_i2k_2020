#@ ImagePlus ctImg

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


image = ImageJFunctions.wrap( ctImg );

long[] translation = new long[]{ 0, 800 };
translatedImg = Views.interval( 
		Views.translate( Views.extendZero( image ),translation ),
		new FinalInterval( 1121, 1600 ));

transformAndShow(translatedImg, "ct translated");

def copy( img )
{
	result = new ImagePlusImgFactory( Util.getTypeFromInterval( img )).create( img );
	LoopBuilder.setImages( img, result ).forEachPixel({ x,y -> y.set(x.get()) } as BiConsumer );
	return result;
}

def transformAndShow( img, name )
{
	ip = copy( img ).getImagePlus();
	ip.setTitle( name );
	ip.show();
	return ip;
}