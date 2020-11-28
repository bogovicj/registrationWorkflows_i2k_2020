set moving="..\..\sampleImages\jrc18_demo_sample_data\FCWB_small.nrrd"
set fixed="..\..\sampleImages\jrc18_demo_sample_data\JRC2018_FEMALE_small.nrrd"
set nThreads="2"

set affine_params="Affine.txt"
set bspline_params="Bspline.txt"

%HOMEDRIVE%%HOMEPATH%/elastix-4.9.0-win64/elastix -f %fixed% -m %moving% -out . -threads %nThreads% -p %affine_params%

move result.0.nrrd affine_result.nrrd
move TransformParameters.0.txt affine_TransformParameters.0.txt

%HOMEDRIVE%%HOMEPATH%/elastix-4.9.0-win64/elastix -f %fixed% -m %moving% -out . -threads %nThreads% -t0 affine_TransformParameters.0.txt -p %bspline_params%

move result.0.nrrd bspline_result.nrrd
move TransformParameters.0.txt bspline_TransformParameters.0.txt
