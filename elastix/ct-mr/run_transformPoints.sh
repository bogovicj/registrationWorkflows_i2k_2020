#!/bin/bash
source setVariables.sh

transformix -def point.txt \
    -out . \
    -tp TransformParameters.0.txt

