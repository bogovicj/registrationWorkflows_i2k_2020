#!/bin/bash

moving="../../sampleImages/ct_translated.tif"
fixed="../../sampleImages/mri_2d.tif"

affine_params="AffineMI_2d.txt"
#affine_params="AffineMI_2d_withInit.txt"

nThreads="2"

elastix -f $fixed \
    -m $moving \
    -out . \
    -threads $nThreads \
    -p $affine_params

mv result.0.nrrd ct_to_mr.nrrd
