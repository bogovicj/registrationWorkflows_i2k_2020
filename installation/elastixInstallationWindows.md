# elastix installation (Windows)

## 1. Download

Download the windows binaries for elastix 4.9.0 from [this page](https://github.com/SuperElastix/elastix/releases/tag/4.9.0),
or use this [direct download link](https://github.com/SuperElastix/elastix/releases/download/4.9.0/elastix-4.9.0-win64.zip).

## 2. Extract

Unzip the zip file to `C:\Users\<USERNAME>\elastix-4.9.0-win64`

## 3. Test

* Use the file explorer to navigate to the folder where elastix is installed (see image below)
  * You should see 5 files, including `elastix.exe`, and `transformix.exe`
* Type `cmd` into the address bar and press `<Enter>` to open the command prompt as shown here:

<img src=https://raw.githubusercontent.com/bogovicj/registrationWorkflows_i2k_2020/main/installation/winOpenCmd.png width="500">

* type `elastix --help` in the command prompt and press `<Enter>`.

If you see output that looks like the image below, congrats! You're done.

<img src=https://raw.githubusercontent.com/bogovicj/registrationWorkflows_i2k_2020/main/installation/testElastixCmd.png width="500">

If not, continue to step 4 and 5.

## (optional) 4. Troubleshoot

* [Download the installers for Visual Studio 2015 runtime](https://www.microsoft.com/en-us/download/details.aspx?id=48145)
* Run the installers

Try step 3 again.  If it still isn't working, continue to step 5 and get help from me.

## (optional) 5. Get help
Contact me by [creating a new issue](https://github.com/bogovicj/registrationWorkflows_i2k_2020/issues) in this repo 
There will also be troubleshooting time during the tutorial.
