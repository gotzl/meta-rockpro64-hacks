# meta-rockpro64-hacks

This README file contains information on the contents of the meta-rockpro64-hacks layer.
The layer targets the [PINE64 ROCKPro64 board](https://www.pine64.org/?page_id=61454).
It a fork of [csonsino/meta-rockpro64-hacks](https://github.com/csonsino/meta-rockpro64-hacks) with some
cleanups and stripped down to the minimum to support the board.
Please see the readme in the original project and the corresponding sections below for details.

## Dependencies

This layer depends on:

* URI: meta-rockchip
* branch: yocto-next

## Table of Contents

  I. Configure yocto/oe environment  
 II. Changes for the RockPro64 implemented in this layer
III. Booting your device
 IV. Booting your device

### I. Configure yocto/oe environment
This layer adds support for the Pine RockPro64, which is not fully supported by
the Rockchip BSP layer. To build for this device, use

MACHINE ?= "rockpro64"

in your local.conf file. Then follow the instructions in the meta-rockchip layer.

### II. Changes for the RockPro64 implemented in this layer
(I assume here, that the RockPro64 is equiped with an eMMC)

First, the kernel tree maintained by [rockchip]() is substituted by the kernel tree maintained by [ayufan](https://github.com/ayufan-rock64/linux-kernel).
Two custom patches are applied to the kernel
1. append 'root=/dev/mmcblk1p4' to the kernel command line
2. set the dmc frequencies back to the ones used in rockchip's kernel tree

I've also added the firmware for the ap6359sa BT/WIFI module.

### III. Booting your device
For convinience, here a short summary how to flash/boot the image using [upgrade_tool](http://opensource.rock-chips.com/wiki_Upgradetool) (for more details see meta-rockchip layer).

1. Connect the RockPro64 via the USB-C port to your PC and put it into rockusb mode
  * if eMMC short the two pins next to the eMMC and the SPI Flash
  * if SD, remove it
  and reset/power-on the device.
2. Go to miniloader
  $ upgrade_tool db \<IMAGE PATH\>/loader.bin
3. Flash the image
  $ upgrade_tool wl 0 \<IMAGE PATH\>/\<IMAGE NAME\>.wic
4. Reset the device
  $ upgrade_tool rd

### IV. Debugging
screen /dev/ttyUSB0 115200
busybox microcom -s 115200 /dev/ttyUSB0 
