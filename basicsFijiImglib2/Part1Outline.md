# Basics, Fiji, and imglib2

Students will learn to write code to do the operations below in imglib2, and how
to integrate that code into Fiji.

## Types of transformations

* Linear
    * Translation
    * Rigid
    * Similarity
    * Affine
* Non-linear
    * Displacement field
    * B-spline
    * Thin plate spline

We will not cover:
* Piecewise linear
* Elastic
* Fluid

but be aware that these exist.

## Operations on transformations

* Inverse transformations
* Concatenating / composing transformations
    * `interpolateOnceDemo.groovy`

## Transforming images and coordinates

* Transforming points 
* Transforming images
* Image metadata and working in physical coordinates

## Homework

* Write code that creates and affine transform that (see `transformImagesHomework.groovy` for a starting point).
    * Flips and image horizontally about its center
    * Flips and image vertically about its center
    * Resizes an image to match another image 
    * Transforms an image so its size is unchanged, but it's center matches that of another image
    * Transforms an image in some creative wa
* Read the API for [`RealTransformSequence`](https://javadoc.scijava.org/ImgLib2/net/imglib2/realtransform/RealTransformSequence.html) and compose an affine transform and a deformation field.
* Look at the functions in `sampleDeformationFields.groovy`
    * Create a function that makes a deformation field that translate an image 5.5 pixels to the left.
    * Create a fun deformation field.
