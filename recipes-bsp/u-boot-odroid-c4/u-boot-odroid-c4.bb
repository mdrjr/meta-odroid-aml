SUMMARY = "ODROID-C4 U-Boot Pre-Built Binary and Boot Files"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = " \
    file://u-boot.bin \
    file://boot.ini \
    file://boot-logo.bmp.gz;unpack=false \
"

S = "${UNPACKDIR}"

inherit deploy nopackages

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${UNPACKDIR}/u-boot.bin "${DEPLOYDIR}/u-boot.bin"
    install -m 0644 ${UNPACKDIR}/boot.ini "${DEPLOYDIR}/boot.ini"
    install -m 0644 ${UNPACKDIR}/boot-logo.bmp.gz "${DEPLOYDIR}/boot-logo.bmp.gz"
}

addtask do_deploy before do_build

ALLOW_EMPTY:${PN} = "1"
COMPATIBLE_MACHINE = "odroid-c4"
