#@ String (choices={"source", "sink", "sinusoid", "spiral", "rotation", "stupid"}, style="radioButtonVertical") type
#@ String (label="size") size

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


sz = size.split(",").collect{ Long.parseLong( it )};
center = sz.collect{ (double)it / 2.0 };

switch (type) {
	case "source": dfield=makeSourceSink(center, 10); break;
	case "sink": dfield=makeSourceSink(center, -10); break;
	case "sinusoid": dfield=makeSinusoid( sz ); break;
	case "spiral": dfield=makeSpiral( sz, center ); break;
	case "rotation": dfield=makeRotation( sz, center ); break;
	case "stupid": dfield=makeStupid( ); break;
	default: println( "not found"); break;
}

interval = new FinalInterval( (sz + 2) as long[] );

toImagePlus( type, Views.interval( dfield, interval )).show()

def toImagePlus( name, img )
{
	result = new ImagePlusImgFactory( Util.getTypeFromInterval( img )).create( img );
	LoopBuilder.setImages( img, result ).forEachPixel({ x,y -> y.set(x.get()) } as BiConsumer );
	ip = result.getImagePlus();
	ip.setDimensions( 1, 2, 1 );
	ip.setTitle( name );
	return ip;
}

def makeSourceSink( center, param )
{
	return new FunctionRandomAccessible( 
	3,
	(p,v) -> {
			double x = p.getDoublePosition( 0 );
			double y = p.getDoublePosition( 1 );
			int d = p.getIntPosition( 2 );
			double d2 = ( (x-center[0])*(x-center[0]) + (y-center[1])*(y-center[1]) );
			double w  = param * Math.exp( -d2 / (10*center[0])  );
			double sign = p.getDoublePosition( d ) > 128 ? 1 : -1;
			v.setReal( w * sign );
		} as BiConsumer,
	FloatType::new );
}


def makeSinusoid( sz )
{
	return new FunctionRandomAccessible( 
	3,
	(p,v) -> {
			int d = p.getIntPosition( 2 );
			pos = p.getDoublePosition( d );
			double f = 4 * Math.PI / sz[ d ];

			displacement = 10 * Math.sin( f * pos );
			v.setReal( displacement );
		} as BiConsumer,
	FloatType::new );
}


def makeRotation( sz, center )
{
	// not efficient, but who cares
	return new FunctionRandomAccessible( 
	3,
	(p,v) -> {
			pt = [p.getDoublePosition( 0 ) - center[ 0 ],
				  p.getDoublePosition( 1 ) - center[ 1 ]];

			ptRot = [ -pt[1], pt[0] ];
			double dist = Math.sqrt((pt[0]*pt[0]) + (pt[1]*pt[1]));
			
			mindist = 10;
			maxAmount = 10.0;
			if( dist < mindist ){
				v.setReal( 0 );
				return;
			}

			maxdist = sz[0] > sz[1] ? 0.8*sz[0] : 0.8*sz[1];

			amount = maxAmount;
			if( dist < maxdist )
			{
				
				amount = ( dist - mindist ) * ( maxAmount / (maxdist-mindist) );
			}
			
			int d = p.getIntPosition( 2 );
			if( d == 0 )
			{
				v.setReal( -pt[1] );
			}
			else
			{
				v.setReal( pt[0] );
			}
			
		} as BiConsumer,
	FloatType::new );
}


def makeSpiral( sz, center )
{
	// not efficient, but who cares
	return new FunctionRandomAccessible( 
	3,
	(p,v) -> {
			pt = [p.getDoublePosition( 0 ) - center[ 0 ],
				  p.getDoublePosition( 1 ) - center[ 1 ]];

			vec = [ (pt[1]-pt[0]), (-pt[0]-pt[1]) ];
			vecsz  = Math.sqrt((vec[0]*vec[0]) + (vec[1]*vec[1])); 
			vecnrm = [vec[0]/vecsz, vec[1]/vecsz];
				  
			dist = Math.sqrt((pt[0]*pt[0]) + (pt[1]*pt[1])); 
			//maxdist = sz[0] > sz[1] ? 0.3*sz[0] : 0.3*sz[1];
			maxdist = sz[0] > sz[1] ? 0.8*sz[0] : 0.8*sz[1];

			amount = (2.0 / maxdist) * dist * dist;
			if( amount > maxdist )
			{
				amount = maxdist;
			}
			
			int d = p.getIntPosition( 2 );  
			if( d == 0 )
			{
				v.setReal( amount * vecnrm[0] );
			}
			else
			{
				v.setReal( amount * vecnrm[1] );
			}
			
		} as BiConsumer,
	FloatType::new );
}

def makeStupid()
{
	return new FunctionRandomAccessible( 
	3,
	(p,v) -> {

			int d = p.getIntPosition( 2 );  
			if( d == 0 )
			{
				v.setReal( -p.getDoublePosition( 0 ) );
			}
			else
			{
				v.setReal(-p.getDoublePosition( 1 ) );
			}
			
		} as BiConsumer,
	FloatType::new );
}
