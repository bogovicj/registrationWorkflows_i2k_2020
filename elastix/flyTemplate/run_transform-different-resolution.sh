#!/bin/bash
source ../setVariables.sh

moving="../../sampleImages/jrc18_demo_sample_data/FCWB_small.nrrd"
fixed="../../sampleImages/jrc18_demo_sample_data/JRC2018_FEMALE_small.nrrd"

movingLo="../../sampleImages/jrc18_demo_sample_data/FCWB_small_downsampled.nrrd"
fixedLo="../../sampleImages/jrc18_demo_sample_data/JRC2018_FEMALE_small_downsampled.nrrd"
nThreads="2"

## Notice that we don't need to give both affine and bspline transform parameters

## PART 1
## Transform the low-res moving image with the low res transform
#transformix \
#    -in $movingLo \
#    -tp bspline_downsampled_TransformParameters.0.txt \
#    -out .

## PART 2
## Transform the high-res moving image with the low res transform
#transformix \
#    -in $moving \
#    -tp bspline_downsampled_TransformParameters.0.txt \
#    -out .

## PART 3
## Modify the file bspline_downsampled_TransformParameters.0.txt so that it renders the result at high resolution
#transformix \
#    -in $moving \
#    -tp bspline_downsampled_TransformParameters_highRes.0.txt \
#    -out .

## PART 4
## Predict what this will do before running it
#transformix \
#    -in $movingLo \
#    -tp bspline_downsampled_TransformParameters_highRes.0.txt \
#    -out .
