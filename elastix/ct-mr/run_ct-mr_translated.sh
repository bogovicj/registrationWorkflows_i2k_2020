#!/bin/bash
source setVariables.sh

moving="../../sampleImages/ct_translated.tif"
fixed="../../sampleImages/mri_2d.tif"

#affine_params="AffineMI_2d_better.txt"
affine_params="AffineMI_2d_withInit.txt"

## Try this later?
#affine_params="RigidMI_2d_better.txt"

nThreads="2"

elastix -f $fixed \
    -m $moving \
    -out . \
    -threads $nThreads \
    -p $affine_params

mv result.0.nrrd ct_to_mr.nrrd
