# Elastix 

Students will learn how to use elastix to automatically register images, and
apply those transformations to images and points.

## Automatic image registration (elastix)

* Running elastix
* Customizing algorithm parameters
    * Loss functions
        * `ct-mr/run_ct-mr.sh`
    * Transform type
        * `ct-mr/run_ct-mr.sh`
    * Initialization
        * `causeCtMrError.groovy`
        * `ct-mr/run_ct-mr_translated.sh`
    * BSpline transformation parameters

## Applying transformations (transformix)

* Transforming point coordinates
* Transforming images used 
* Transforming images not used in registration 
* Converting a nonlinear transform to a displacement field
    * Apply the transformation with imglib2 in Fiji

## Evaluation quality of registration

* Registration accuracy
    * Point correspondence errors
    * Why to avoid using the loss function directly
    * Measure errors with an independent image channel

* Measure deformation
    * Jacobian determinants 

