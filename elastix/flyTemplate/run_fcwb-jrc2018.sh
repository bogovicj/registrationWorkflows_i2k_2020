#!/bin/bash
source ../setVariables.sh

moving="../../sampleImages/jrc18_demo_sample_data/FCWB_small.nrrd"
fixed="../../sampleImages/jrc18_demo_sample_data/JRC2018_FEMALE_small.nrrd"
nThreads="2"

affine_params="Affine.txt"
bspline_params="Bspline.txt"

elastix -f $fixed \
    -m $moving \
    -out . \
    -threads $nThreads \
    -p $affine_params

mv result.0.nrrd affine_result.nrrd
mv TransformParameters.0.txt affine_TransformParameters.0.txt

elastix -f $fixed \
    -m $moving \
    -out . \
    -threads $nThreads \
    -t0 affine_TransformParameters.0.txt \
    -p $bspline_params

mv result.0.nrrd bspline_result.nrrd
mv TransformParameters.0.txt bspline_TransformParameters.0.txt
