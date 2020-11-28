set moving="..\..\sampleImages\ct_2d_moving.tif"
set fixed="..\..\sampleImages\mri_2d.tif"

set affine_params="AffineMSE_2d.txt"
set nThreads="2"

echo %nThreads%

%HOMEDRIVE%%HOMEPATH%/elastix-4.9.0-win64/elastix -f %fixed% -m %moving% -out . -threads %nThreads% -p %affine_params%
move result.0.nrrd ct_to_mr.nrrd
