set moving="..\..\sampleImages\jrc18_demo_sample_data\FCWB_small_downsampled.nrrd"
set fixed="..\..\sampleImages\jrc18_demo_sample_data\JRC2018_FEMALE_small_downsampled.nrrd"
set nThreads="2"

set affine_params="Affine_faster.txt"
set bspline_params="Bspline_faster.txt"

elastix -f %fixed% -m %moving% -out . -threads %nThreads% -p %affine_params%
move result.0.nrrd affine_downsampled_result.nrrd
move TransformParameters.0.txt affine_downsampled_TransformParameters.0.txt

elastix -f %fixed% -m %moving% -out . -threads %nThreads% -t0 affine_TransformParameters.0.txt -p %bspline_params%	
move result.0.nrrd bspline_downsampled_result.nrrd
move TransformParameters.0.txt bspline_downsampled_TransformParameters.0.txt
