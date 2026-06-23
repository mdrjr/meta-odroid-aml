SUMMARY = "zram swap device set up before weston starts"
DESCRIPTION = "Loads the zram module, creates an lzo-rle compressed swap"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://odroid-zram.sh \
    file://odroid-zram.init \
"

S = "${UNPACKDIR}"

inherit update-rc.d

RDEPENDS:${PN} = "kernel-module-zram"

INITSCRIPT_NAME = "odroid-zram"
INITSCRIPT_PARAMS = "start 04 S . stop 20 0 1 6 ."

do_install() {
    install -Dm0755 ${UNPACKDIR}/odroid-zram.sh \
        ${D}${bindir}/odroid-zram.sh
    install -Dm0755 ${UNPACKDIR}/odroid-zram.init \
        ${D}${sysconfdir}/init.d/odroid-zram
}

FILES:${PN} += "${bindir}/odroid-zram.sh"
