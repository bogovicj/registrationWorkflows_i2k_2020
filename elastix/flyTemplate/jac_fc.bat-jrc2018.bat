set nThreads="2"
set transformParameters="bspline_TransformParameters.0.txt"

transformix -tp %transformParameters% -out . -jac all -threads %nThreads%