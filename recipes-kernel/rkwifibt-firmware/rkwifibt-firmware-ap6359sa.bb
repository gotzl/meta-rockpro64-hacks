# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Rockchip ap6359sa WIFI/BT firmware files"
LICENSE = "LICENSE.rockchip"
LIC_FILES_CHKSUM = "file://${RK_BINARY_LICENSE};md5=5fd70190c5ed39734baceada8ecced26"


SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/kszaq/brcmfmac_sdio-firmware-aml.git \
	file://COPYING.MIT \
"

S = "${WORKDIR}/git"

inherit allarch deploy

do_compile() {
	echo "do nothing"
}

do_install() {
	install -d ${D}/system/etc/firmware/
	install -m 0644 ${S}/firmware/brcm/BCM4359C0.hcd ${D}/system/etc/firmware/
	install -m 0644 ${S}/firmware/brcm/fw_bcm4359c0_ag.bin ${D}/system/etc/firmware/
	install -m 0644 ${S}/firmware/brcm/fw_bcm4359c0_ag_apsta.bin ${D}/system/etc/firmware/	
	install -m 0644 ${S}/firmware/brcm/nvram_ap6359sa.txt ${D}/system/etc/firmware/
}

INSANE_SKIP_${PN} += "arch"
FILES_${PN} = "system/etc/firmware/"