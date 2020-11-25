#!/bin/bash

source setVariables.sh

elastix -f ../sampleImages/castle_gray.jpg \
    -m ../sampleImages/castle_gray.jpg \
    -out . \
    -p testParams.txt

