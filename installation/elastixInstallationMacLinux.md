# elastix installation (Mac/Linux)

## 1. Download

Download the windows binaries for elastix 5.0.1 from [this page](https://github.com/SuperElastix/elastix/releases/tag/5.0.1),
or use these direct links: 
* [Mac](https://github.com/SuperElastix/elastix/releases/download/5.0.1/elastix-5.0.1-mac.zip)
* [Linux](https://github.com/SuperElastix/elastix/releases/download/5.0.1/elastix-5.0.1-linux.tar.bz2)

## 2. Extract

Unzip the zip file to `/Users/<USERNAME>/elastix-5.0.1-mac` and `/home/<USERNAME>/elastix-5.0.1-linux` for mac / linux, respectively. 

## 3. Test

If you haven't yet, download the code in this repository with [this link](https://github.com/bogovicj/registrationWorkflows_i2k_2020/archive/main.zip) and unzip the file (or use `git clone` if you're familiar with git).

* mac 
    * rename `setVariablesMac.sh` to `setVariables.sh`
* linux 
    * rename `setVariablesLinux.sh` to `setVariables.sh`

Open a terminal in the folder `registrationWorkflows_i2k_2020/elastix`.

On a Mac, you can open a terminal from Finder by right-clicking on the folder then choosing `Services > New Terminal at Folder` as shown here:

<img src=https://raw.githubusercontent.com/bogovicj/registrationWorkflows_i2k_2020/main/installation/macStartTerminal.png width="500">

Type `./test.sh` in the terminal and press `<Enter>`.  You should see some output in the terminal, and a new file (`TransformParameters.0.txt`) should appear in the folder.

## 3a. If you see a security warning on Mac osx

Give elastix a security exception as described [here](https://support.apple.com/guide/mac-help/open-a-mac-app-from-an-unidentified-developer-mh40616/mac) by:
1. Go to System preferences with `Apple icon > System Preferences`
2. Go to Security and Privacy
3. Open the General Tab
4. Click the lock to make changes
5. Click the "Open Anyway" button

Try again to type `./test.sh` in the terminal and press `<Enter>`.  You may get another security exception.  If
so, repeat the steps above to grant another exception.

## (optional) 4. Get help
Contact me by [creating a new issue](https://github.com/bogovicj/registrationWorkflows_i2k_2020/issues) in this repo.
There will also be troubleshooting time during the tutorial.

