# Automatic and manual image registration workflows course
Part of the [I2K 2020](https://www.janelia.org/you-janelia/conferences/from-images-to-knowledge-with-imagej-friends) conference. [Apply here](https://www.janelia.org/you-janelia/conferences/from-images-to-knowledge-with-imagej-friends/virtual-workshop-program).

## Sessions: 	
* `2020-11-30 21:00 – 2020-12-01 01:00 UTC`
* `2020-12-01 13:00 – 2020-12-01 17:00 UTC`

# Summary

Students will learn:
* The principals of image transformation and registration [(detailed outline)](https://github.com/bogovicj/registrationWorkflows_i2k_2020/blob/main/basicsFijiImglib2/Part1Outline.md)
    * Types of transformations (similarity, affine, non-linear)
    * Displacement fields for non-linear transformations
    * Basics of imglib2 spatial transformations
* How to use [elastix](https://elastix.lumc.nl/) for automatic image registration [(detailed outline)](https://github.com/bogovicj/registrationWorkflows_i2k_2020/blob/main/elastix/Part2Outline.md)
    * Parameter choices
    * Jacobian of a transformation to measure deformation
* How to use [BigWarp](https://imagej.net/BigWarp) to perform manual registration or to fine-tune an automatic registration [(detailed outline)](https://github.com/bogovicj/registrationWorkflows_i2k_2020/blob/main/bigwarp/Part3Outline.md).
    * concatenating transformations
    * converting transformations to convenient formats for use in different tools.

### Prerequisites 

Students should be familiar with ImageJ/Fiji, and understand basic image analysis concepts such as resampling and interpolation.

1. Download and install Fiji
2. Download and install elastix
   1. [Windows instructions](https://github.com/bogovicj/registrationWorkflows_i2k_2020/blob/main/installation/elastixInstallationWindows.md)
   2. [Mac/Linux instructions](https://github.com/bogovicj/registrationWorkflows_i2k_2020/blob/main/installation/elastixInstallationMacLinux.md)
3. Download the code in this repository with [this link](https://github.com/bogovicj/registrationWorkflows_i2k_2020/archive/main.zip) (or `git clone`).

#### Download sample data

* [Drosophila template samples](https://figshare.com/s/bb156ad5a4288cdc65a2)
* [CT-MR registration samples](https://figshare.com/articles/dataset/CT-MR_Registration_sample_images/13218026)
* [CLEM registration samples](https://figshare.com/s/cca4dea1b4411fb63e94)
