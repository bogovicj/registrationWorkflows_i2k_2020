#!/bin/bash

moving="../../sampleImages/jrc18_demo_sample_data/FCWB_small.nrrd"
fixed="../../sampleImages/jrc18_demo_sample_data/JRC2018_FEMALE_small.nrrd"
nThreads="2"


transformParameters="bspline_TransformParameters.0.txt"

transformix -tp $transformParameters \
    -out . \
    -jac all \
    -threads $nThreads

