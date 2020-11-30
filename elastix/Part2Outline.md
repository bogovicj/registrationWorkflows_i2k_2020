# Elastix 

Students will learn how to use elastix to automatically register images, and
apply those transformations to images and points.

## Automatic image registration (elastix) and applying transformations

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

## Applying transformations to points

* Transforming point coordinates (`ct-mr` folder)
    * Make sure you have a `TransformParameters.0.txt` result in the folder.
    * Look at `point.txt` for how transformix expects its input.
    * Predict what will hapen when we transform the point.
    * Run `run_transformPoints.sh`, observe results and compare with your prediction.
    * Try `run_transformPoints.sh` with a different input.

## Converting the transform
 
* Converting a nonlinear transform to a displacement field (`ct-mr` folder)
    * Look at and run `run_toDfield.sh`
    * Open `deformationField.nrrd` in Fiji.
* Apply the transformation with imglib2 in Fiji (`ct-mr` folder)
    * Draw an roi on the moving ct image.
    * Run `basicsFijiImglib2/transformPointsAndImagesDfield.groovy` and observe the results.

## Evaluation quality of registration

* Registration accuracy
    * Point correspondence errors
    * Why to avoid using the loss function directly
    * Measure errors with an independent image channel

* Measure deformation with the Jacobian determinant
    * Bspline parameters (`flyTemplate` folder)
        * Run `run_fcwb-jrc2018.sh` with `Bspline.txt` if we havn't already.
        * Run `jac_fcwb-jrc2018.sh` and look at the jacobian determinant.
        * Run `run_fcwb-jrc2018_first.sh` with `Bspline_moreWarping.txt` and observe the results.
        * Run `jac_fcwb-jrc2018.sh` again using the new result and look at the jacobian determinant, compare with the previous.

## "Homework"

* Set up a two-step (rigid+bspline) registration for the `ct-mr` example.
    * and find a set of bspline parameters that look good.
* Find two ways to change `flyTemplate/Affine.txt` so that it fails.
* Edit one of the `TransformaParameters` files to *upsample* the output by a factor of two.
* Transform 3d points
    * use `transformix` to transform the points in `GadMARCM-F000122_seg001_03_swc_forTransformix.txt`

## Resources

* [elastix wrapper for Fiji](https://github.com/embl-cba/elastixWrapper) from Christian Tischer.
* [elastix manual](https://elastix.lumc.nl/download/elastix-5.0.1-manual.pdf)

## For plotting 
Install [Rstudio](https://rstudio.com/) or [R](https://www.r-project.org/)
and the [tidyverse](https://www.tidyverse.org/) with
```
install.packages("tidyverse")
```
