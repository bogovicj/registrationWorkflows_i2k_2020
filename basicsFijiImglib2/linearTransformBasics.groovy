import java.util.function.*;

import bdv.util.*;
import net.imglib2.*;
import net.imglib2.img.*;
import net.imglib2.img.imageplus.*;
import net.imglib2.img.display.imagej.*;
import net.imglib2.interpolation.randomaccess.*;
import net.imglib2.type.numeric.*;
import net.imglib2.view.*;
import net.imglib2.realtransform.*;
import net.imglib2.loops.*;
import net.imglib2.util.*;

/**
 * CONCLUSIONS:
 * 
 * linear transforms can be written as matrices
 * in this class, they will always be invertible
 * inverses are "applied in reverse order"
 *    Inverses in reverse order is true also for invertible, 
 *    non-linear transformations
 */
 
 
/*
 * Some transformations
 */

// identity
id = new AffineTransform2D(); 

// rotate by 0.25*pi radians
translation = new AffineTransform2D();
translation.translate( -12, 24 ); 

// rotate by 0.25*pi radians
rot = new AffineTransform2D();
rot.rotate( 3.141592 / 4 ); 

// scale x by 2 and y by 0.5
scaleXandY = new AffineTransform2D();
scaleXandY.scale( 2, 0.5 );

// scale x by 2 and y by 0.5
scale = new AffineTransform2D();
scale.scale( 1.5 );


/*
 * A nice way to think about transformations is 
 * as a matrix.
 * 
 * Here's the identity:
 */

printAsMatrix( id );

/* 
 * Why is our 2d transform a 3x3 matrix?
 *   this lets us to apply translations as matrix multiplications
 * 
 * Exercise work out this matrix multiplication
 * | 1 0 tx ||x| 
 * | 0 1 ty ||y| = ?
 * | 0 0  1 ||1|
 */

printAsMatrix( translation );


/*
 * input and output variables
 */
input = new RealPoint( 3.2, 5.4 );
output = new RealPoint( 2 ); // a 2d point

/*
 * Demonstrate properties of transforms
 */

// the identity
id.apply( input, output );
println( output );


// transforms do not commute (order matters)
A = scaleXandY;
B = rot;

AthenB = A.copy();
AthenB.preConcatenate( B );

BthenA = B.copy();
BthenA.preConcatenate( A );

// notice that these outputs are different
AthenB.apply( input, output );
println( 'AthenB : ' + output );

BthenA.apply( input, output );
println( 'BthenA : ' + output );




/*
 * Why did we need 'preConcatenate' above?
 * because of matrix multiplication conventions
 * 
 * if A and B are matrices
 * ABx = A(Bx)
 * i.e. apply B to x first, then apply A to the result
 * 
 * and
 * 
 * BAx = B(Ax)
 * i.e. apply A to x first, then apply B to the result
 * 
 * A.preConcatenate(B) means left multiply A by B 
 * = BA
 * 
 * A.concatenate(B) means right multiply A by B 
 * = AB
 * 
 * this means that 
 * B.concatenate(A)
 * is the same as
 * A.preConcatenate(B)
 * see:
 */

AthenB1 = A.copy();
AthenB1.preConcatenate( B );

AthenB2 = B.copy();
AthenB2.concatenate( A );

println( 'AthenB1: ' + AthenB1 )
println( 'AthenB2: ' + AthenB2 )

/*
 * Some transformations do commute though
 */
A = scale;
B = rot;

AthenB = A.copy();
AthenB.preConcatenate( B );

BthenA = B.copy();
BthenA.preConcatenate( A );

// notice that these outputs are different
AthenB.apply( input, output );
println( 'scale then rotate : ' + output );

BthenA.apply( input, output );
println( 'rotate then scale : ' + output );


/*
 * The transforms we've used so far have inverses
 * 
 * x
 * Ax = y 
 * BAx = x
 */

input = new RealPoint( 3.2, 5.4 );
inputA = new RealPoint( 2 );
inputAAinv = new RealPoint( 2 );

A = rot;
A.apply( input, inputA );
A.inverse().apply( inputA, inputAAinv );

println( 'input      : ' + input );
println( 'inputA     : ' + inputA );
println( 'inputAAinv : ' + inputAAinv );

// or:
// A.applyInverse( inputAAinv, inputA );

/*
 * Not every transform is invertible, but imglib2 will throw an error
 * if you try to create a non-invertible transform:
 */
//notInvertible = new AffineTransform2D();
//notInvertible.set( 1, 1, 0, 2, 2, 0 );


/*
 * If you have multiple transforms, inverses are applied in reverse order:
 * (AB)^-1 = (B^-1)(A^-1)
 */
tmp = new RealPoint( 2 ); // a 2d point

A = scaleXandY;
B = rot;

BthenA = B.copy();
BthenA.preConcatenate( A );
BthenA.applyInverse( output, input );
println( "(AB)^-1 ": output );

/*
 * WARNING DON'T DO THE BELOW WITH AFFINES
 * INSTEAD DO THE ABOVE:
 * BthenA.applyInverse( output, input );
 */
Ainv = A.inverse();
Binv = B.inverse();

Binv.apply( input, tmp );
Ainv.apply( tmp, output );
// not the same as (AB)^1
println( "(B^-1)(A^-1)": output );

Ainv.apply( input, tmp );
Binv.apply( tmp, output  );
println( "(A^-1)(B^-1)": output );


/******************************
 *	ONLY FUNCTIONS BELOW HERE *
 ******************************/
 
def printAsMatrix( a )
{
	println( ' ' );
	println( [ a.get(0,0), a.get(0,1), a.get(0,2) ].join(" "));
	println( [ a.get(1,0), a.get(1,1), a.get(1,2) ].join(" "));
	println( [0.0, 0.0, 1.0 ].join(" "));
	println( ' ' );
}