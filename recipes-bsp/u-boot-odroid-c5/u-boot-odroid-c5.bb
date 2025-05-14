SUMMARY = "ODROID-C5 BootScripts and U-Boot Pre-Built Binaries"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "u-boot-mkimage-native"
INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "file://u-boot.bin.signed \
          file://boot.scr.txt"

do_compile() {
    mkimage -A arm64 -T script -C none -n "BootScript" -d "${UNPACKDIR}/boot.scr.txt" boot.scr
}

inherit kernel-arch deploy nopackages

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_deploy() { 
    install -d ${DEPLOYDIR}
    install -m 0644 u-boot.bin.signed "${DEPLOYDIR}"/u-boot.bin.signed
    install -m 0644 boot.scr "${DEPLOYDIR}"/boot.scr
}

addtask do_deploy after do_compile before do_build

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-dev = "1"
ALLOW_EMPTY_${PN}-staticdev = "1"
