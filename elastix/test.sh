#!/bin/bash

elastix -f ../sampleImages/castle_gray.jpg \
    -m ../sampleImages/castle_gray.jpg \
    -out . \
    -p testParams.txt

