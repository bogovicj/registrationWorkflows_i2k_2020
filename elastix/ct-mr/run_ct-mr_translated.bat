#!/bin/bash

set moving="..\..\sampleImages\ct_translated.tif"
set fixed="..\..\sampleImages\mri_2d.tif"

set affine_params=AffineMI_2d_withInit.txt"
set nThreads="2"

echo %nThreads%

%HOMEDRIVE%%HOMEPATH%/elastix-4.9.0-win64/elastix -f %fixed% -m %moving% -out . -threads %nThreads% -p %affine_params%
move result.0.nrrd ct_to_mr.nrrd
