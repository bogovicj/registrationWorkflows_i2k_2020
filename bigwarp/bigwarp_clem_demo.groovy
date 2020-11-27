import ij.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.realtransform.*;
import net.imglib2.util.*;

import bdv.util.*;
import bdv.viewer.*;
import bdv.ij.util.*;
import bigwarp.*;
	

IJ.selectWindow( "em.tif" );
emImg = IJ.getImage();

IJ.selectWindow( "PALM_532nm_lo.tif" );
mitoImg = IJ.getImage();

IJ.selectWindow( "PALM_488nm_lo.tif" );
erImg = IJ.getImage();

def buildSource( ip )
{
	AffineTransform3D transform = new AffineTransform3D()
	transform.set( ip.getCalibration().pixelWidth, 0, 0 );
	transform.set( ip.getCalibration().pixelHeight, 1, 1 );
	transform.set( ip.getCalibration().pixelDepth, 2, 2 );

	// origin
	transform.set( ip.getCalibration().xOrigin, 0, 3 );
	transform.set( ip.getCalibration().yOrigin, 1, 3 );
	transform.set( ip.getCalibration().zOrigin, 2, 3 );

	img = ImageJFunctions.wrap( ip );
	return new RandomAccessibleIntervalSource(
		img, Util.getTypeFromInterval( img ), transform, ip.getTitle());
}


emSrc = buildSource( emImg );
mitoLmSrc = buildSource( mitoImg );
erLmSrc = buildSource( erImg );


bwdata = BigWarpInit.createBigWarpData( 
		[ emSrc ] as Source[],
		[ mitoLmSrc, erLmSrc ] as Source[],
		[ "em",  "mito", "er"] as String[] );


bw = new BigWarp( bwdata, "i2k 2020 clem demo", new ProgressWriterIJ());
