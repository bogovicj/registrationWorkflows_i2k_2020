#@ Dataset image

import bdv.util.*;
import net.imglib2.*;
import net.imglib2.img.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.type.numeric.*;
import net.imglib2.view.*;
import net.imglib2.realtransform.*;

/* translate +40.5 pixels in x and -20.5 pixels in y */
transform = new Translation2D( 40.5, -20.5 );

/* "shrink" x-axis by 30%, "expand" y-axis by 30% */
//transform = new Scale2D( 0.7, 1.3 )

/* build a rotation */
//transform = new AffineTransform2D() // identity
//transform.rotate( 3.141592 / 4 ) // rotate by 0.25*pi radians


/*
 * How to transform images with imglib2
 */
RealRandomAccessible transformedImage = 
	RealViews.transform( 
		Views.interpolate( 
			Views.extendZero( image ),
			new NLinearInterpolatorFactory() ), 
		transform );

/*
 * Visualization specific code below
 */
BdvOptions opts = BdvOptions.options();
if( image.numDimensions() == 2 )
	opts = opts.is2D();
	
BdvStackSource bdv = BdvFunctions.show( image, "original", opts );
BdvFunctions.show( transformedImage, image, "transformed", opts.addTo( bdv ) );

ARGBType red = new ARGBType( ARGBType.rgba( 255, 0, 0, 255 ) );
bdv.getBdvHandle().getSetupAssignments().getConverterSetups().get( 1 ).setColor( red );