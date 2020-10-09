SUMMARY = "Rockchip ap6359sa WIFI/BT firmware files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/LibreELEC/brcmfmac_sdio-firmware.git \
	file://COPYING.MIT \
"

S = "${WORKDIR}/git"

inherit allarch

do_compile() {
	echo "do nothing"
}

do_install() {
	install -d ${D}/lib/firmware/brcm
	install -m 0644 ${S}/brcmfmac4359-sdio.bin ${D}/lib/firmware/brcm
	install -m 0644 ${S}/brcmfmac4359-sdio.txt ${D}/lib/firmware/brcm
}

INSANE_SKIP_${PN} += "arch"
FILES_${PN} = "/lib/firmware/brcm"
