# Bigwarp

## Basics

* Working with landmarks
* Saving transformation
    * Saving landmarks 
    * Exporting the transform as a deformation field
* Exporting warped images
    * detailed options
* General tips

## Transforming points and ROIs

* [Script for warping 2d rois](https://raw.githubusercontent.com/bogovicj/registrationWorkflows_i2k_2020/main/bigwarp/Apply_Bigwarp_Xfm_IjRoi2d.groovy)
* [Script for warping points in a csv](https://raw.githubusercontent.com/saalfeldlab/bigwarp/master/scripts/Apply_Bigwarp_Xfm_csvPts.groovy)
* [Documentation and examples](https://imagej.net/plugins/bigwarp#apply-transforms)

## Fine-tuning an automatic transformation 

* Running Bigwarp starting from an automatic result

## "Homework"

* Confirm that the exported deformation field works properly.

### A challenging task that brings it all together (`cos7_clemSamples`)

The goal is to apply a chain of transformations to high-resolution raw data to avoid interpolating multiple times.
Two things 

#### (A) Figure out how to apply the automatic transformation correctly

1. Use transformix to convert `BSplineParameters.txt` to a deforamation field, ("dfield.tif")
2. Apply the inverse of `reorientation.mat` to `emZoomRaw.tif`
3. Apply `dfield.tif` to the result of (2).
4. Make sure the result matches `em.tif`
5. Concatenate `dfield.tif` and the inverse of `reorientation.mat` (make sure you get the order right).

#### (B) Use bigwarp to fine-tune the automatic result

Use bigwarp to improve the registration around the mito in `emZoomRaw.tif`

1. Run bigwarp with `em.tif` as the moving image and `PALM_532nm_lo.tif` as the fixed image.
2. Set landmarks to improve the registration around the mito of interest in `emZoomRaw.tif`.
3. Save the landmarks.

#### (C) Concatenate and apply all the transformations

1. Concatenate `dfield.tif` and the inverse of `reorientation.mat`

