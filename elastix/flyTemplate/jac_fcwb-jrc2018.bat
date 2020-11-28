set nThreads="2"
set transformParameters="bspline_TransformParameters.0.txt"

%HOMEDRIVE%%HOMEPATH%/elastix-4.9.0-win64/transformix -tp %transformParameters% -out . -jac all -threads %nThreads%
