# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/linux-rockchip.inc


SRC_URI = " \
	git://github.com/ayufan-rock64/linux-kernel;protocol=git;branch=release-4.4.202;nocheckout=1 \
	"
SRCREV = "d3f1be0ed310d118ccf04cf9b691c92a914a97a9"

LINUX_VERSION = "4.4"
LINUX_VERSION_EXTENSION_append = "-ayufan"
KERNEL_VERSION_SANITY_SKIP="1"


SRC_URI += " \
	file://docker.cfg \
	file://various.cfg \
	${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', \
		   ' file://ext4.cfg', '', d)} \
	"
KERNEL_DEFCONFIG_rockpro64 = "${WORKDIR}/defconfig"