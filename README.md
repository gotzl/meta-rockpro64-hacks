# meta-rockpro64-hacks

This README file contains information on the contents of the meta-rockpro64-hacks layer.
The layer targets the [PINE64 ROCKPro64 board](https://www.pine64.org/?page_id=61454).
It's a fork of [csonsino/meta-rockpro64-hacks](https://github.com/csonsino/meta-rockpro64-hacks) with some
cleanups and it's stripped down to the minimum to support the board.
Please see the readme in the original project and the corresponding sections below for details.

## Dependencies

This layer depends on:

* URI: meta-rockchip
* branch: dunfell

## Table of Contents

  I. Configure yocto/oe environment  
 II. Changes for the RockPro64 implemented in this layer  
 III. Booting your device  
 IV. Booting your device

### I. Configure yocto/oe environment
This layer adds support for the Pine RockPro64, which is not fully supported by
the meta-rockchip layer. To build for this device, use

```
MACHINE ?= "rockpro64"
```

in your local.conf file.

### II. Changes for the RockPro64 implemented in this layer
(I assume here, that the RockPro64 is equiped with an eMMC)

First, this uses the linux-yocto kernel with a patch to get ap6359sa BT/WIFI module working.
It also uses the most recent u-boot, which enables basically all features you'd expect (USB/HDMI/...).
Finally, the wic generation is broken and the device won't boot with the fitImage. This is not fixed
yet and step III. above needs an additional step... To create the boot.img mentioned there, do s.t. like this:

```bash
mkdir -p boot/extlinux
cp linux-rockpro64-standard-build/arch/arm64/boot/Image  boot/Image 
cp linux-rockpro64-standard-build/arch/arm64/boot/dts/rockchip/rk3399-rockpro64.dtb boot/rk3399-rockpro64.dtb 
cat <<EOF > boot/extlinux/extlinux.conf
label rockchip-kernel-5.6
    kernel /Image
    fdt /rk3399-rockpro64.dtb
    append earlycon=uart8250,mmio32,0xff1a0000 root=/dev/mmcblk2p7 usb-storage.quirks=0x0bc2:0x231a:u rootwait rootfstype=ext4 init=/sbin/init
EOF
genext2fs -b 32768 -d boot/ -i 8192 -U boot.img
```
If you don't have a BT/WIFI module, use `/dev/mmcblk1p7`.

### III. Booting your device
For convinience, here a short summary how to flash/boot the image using [upgrade_tool](http://opensource.rock-chips.com/wiki_Upgradetool).

1. Connect the RockPro64 via the USB-C port to your PC and put it into rockusb mode
  * if eMMC short the two pins next to the eMMC and the SPI Flash
  * if SD, remove it
  and reset/power-on the device.
2. Go to miniloader  
  $ upgrade_tool db \<IMAGE PATH\>/loader.bin
3. Flash the image  
    a. $ upgrade_tool wl 0 \<IMAGE PATH\>/\<IMAGE NAME\>.wic  
    b. $ upgrade_tool wl 0x8000 boot.img
4. Reset the device  
  $ upgrade_tool rd

### IV. Debugging
Use either one of the folling commands after wireing the serial like explained [here](https://forum.pine64.org/showthread.php?tid=6387):
```
screen /dev/ttyUSB0 1500000
busybox microcom -s 1500000 /dev/ttyUSB0 
```
