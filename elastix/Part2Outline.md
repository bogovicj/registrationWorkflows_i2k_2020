# Elastix 

Students will learn how to use elastix to automatically register images, and
apply those transformations to images and points.

## Automatic image registration (elastix)

* Running elastix
* Customizing algorithm parameters
    * Loss functions
        * Run `ct-mr/run_ct-mr.sh` with `AffineMSE_2d.txt` and observe results.
        * Run `ct-mr/run_ct-mr.sh` with `AffineMI_2d.txt` and observe results.
    * Transform type
        * Run `ct-mr/run_ct-mr.sh` with `RigidMI_2d.txt` and observe results.
        * Run `ct-mr/run_ct-mr.sh` with `RigidMI_2d_better.txt` and observe results.
        * Run `ct-mr/run_ct-mr.sh` with `AffineMI_2d_better.txt` and observe results.
        * Draw conclusions
    * Initialization
        * Run `causeCtMrError.groovy` with `ct_2d_moving.tif` and save the image.
        * Run `ct-mr/run_ct-mr_translated.sh` with `AffineMI_2d_better.txt` and observe results.
        * Run `ct-mr/run_ct-mr_translated.sh` with `AffineMI_2d_withInit.txt` and observe results.
    * Multi-step registration
        * Run `flyTemplate/run_fcwb-jrc2018_first.sh` and observe the results.
        * Run `flyTemplate/run_fcwb-jrc2018.sh` and observe the results.
    * Bspline parameters

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

## "Homework"

* Set up a two-step (rigid+bspline) registration for the `ct-mr` example.
    * and find a set of bspline parameters that look good.
* Find two ways to change `flyTemplate/Affine.txt` so that it fails.


## For plotting 
Install [Rstudio](https://rstudio.com/) or [R](https://www.r-project.org/)
and the [tidyverse](https://www.tidyverse.org/) with
```
install.packages("tidyverse")
```
