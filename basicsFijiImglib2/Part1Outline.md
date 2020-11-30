# Basics, Fiji, and imglib2

Students will learn to write code to do the operations below in imglib2, and how
to integrate that code into Fiji.

## Types of transformations

* Open and run `simpleImglib2Transforms.groovy`
* Define different types of transformations
    * Translation, Rigid, Similarity, Affine
* Properties of linear transformations `linearTransformBasics.groovy`
    * linear transforms can be written as matrices
    * in this class, they will always be invertible
    * inverses are "applied in reverse order"
        * Inverses in reverse order is true also for invertible, non-linear transformations

* Displacement field
    * Open `mysteryDeformation.nrrd` and `grid2d.tif` from `sampleImages` folder
    * What is a displacement field?
    * Predict what will happen when we transform the grid with the deformation `deformationFields.groovy`
        * were you right?
    * [Transforms on points vs images](https://github.com/bogovicj/transforms_tutorial/blob/master/resources/2019_DAIS.pdf)
        * **To transform an image, you need to transform points from target to moving image space**
    
* B-spline (elastix, pt2)
* Thin plate spline (Bigwarp, pt3)


We will not cover:
* Piecewise linear
* Elastic
* Fluid

but be aware that these exist.

## Operations on transformations

* Inverse transformations
* Concatenating / composing transformations
    * `interpolateOnceDemo.groovy`
    * See this [famous tweet](https://twitter.com/haesleinhuepf/status/1088546103866388481?s=20)

## Transforming images and coordinates

* Transforming points and images `transformPointsAndImages.groovy`, `transformPointsAndImagesDfield.groovy`.
* Image metadata and working in physical coordinates (We'll see this in part 2).

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
* Our deformation scripts will not work for the `flyTemplate` elastix example.  Make them work by:
    * extending to 3D
    * make the deformations fields work in physical, not pixel coordinates.

## Resources
* [some command line and fiji tools for transformations](https://github.com/saalfeldlab/template-building/wiki/Usage-examples)
* [hdf5/n5 displacement fields](https://github.com/saalfeldlab/template-building/wiki/Hdf5-Deformation-fields)
* [My DAIS 2019 tutorial](https://github.com/bogovicj/transforms_tutorial)
    * [slides explaining why the inverse transform is needed for images](https://github.com/bogovicj/transforms_tutorial/blob/master/resources/2019_DAIS.pdf)
