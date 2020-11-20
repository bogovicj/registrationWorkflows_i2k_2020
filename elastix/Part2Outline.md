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
    * Multi-step registration (`flyTemplate` folder)
        * Run `run_fcwb-jrc2018_first.sh` and observe the results.
        * Run `run_fcwb-jrc2018.sh` and observe the results.
    * Use your image metadata (and speeding up registration) (`flyTemplate` folder)
        * Run `downsampleSimple.groovy` to downsample `JRC2018_FEMALE_small.nrrd` and `FCWB_small.nrrd` by 4.
        * Run `run_fcwb-jrc2018_downsampled.sh` and observe results.
        * Run `run_transform-different-resolution.sh` "Part 1" and observe results.
        * Run `run_transform-different-resolution.sh` "Part 2" and observe results.
        * Run `run_transform-different-resolution.sh` "Part 3" and observe results.
        * Predict what `run_transform-different-resolution.sh` "Part 4" will do, then run it, and observe results.
    * Bspline parameters (`flyTemplate` folder)
        * Run `run_fcwb-jrc2018.sh` with `Bspline.txt` if we havn't already.
        * Run `jac_fcwb-jrc2018.sh` and look at the jacobian determinant.
        * Run `run_fcwb-jrc2018_first.sh` with `Bspline_moreWarping.txt` and observe the results.
        * Run `jac_fcwb-jrc2018.sh` again using the new result and look at the jacobian determinant, compare with the previous.

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
* Edit one of the `TransformaParameters` files to *upsample* the output by a factor of two.


## For plotting 
Install [Rstudio](https://rstudio.com/) or [R](https://www.r-project.org/)
and the [tidyverse](https://www.tidyverse.org/) with
```
install.packages("tidyverse")
```
