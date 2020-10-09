FILESEXTRAPATHS_prepend := "${THISDIR}/files:${THISDIR}/${PN}:"

SRC_URI += "file://defconfig"
SRC_URI += " \
	file://docker.cfg  \
	file://ext4.cfg  \
	file://various.cfg \
	file://rockchip.cfg \
	file://wlan.cfg \
	file://brcmfmac-add-support-for-BCM4359-SDIO-chipset.patch \
"
	
COMPATIBLE_MACHINE .= "|firefly-rk3288|marsboard-rk3066|radxarock|rock-pi-4|rock2-square|tinker-board-s|tinker-board|vyasa-rk3288|rockpro64"
RDEPENDS_${PN} += "brmfmac-sdio-firmware"
