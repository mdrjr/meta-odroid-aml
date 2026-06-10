SUMMARY = "zram swap device set up before weston starts"
DESCRIPTION = "Loads the zram module, creates an lzo-rle compressed swap \
device sized to RAM and enables it early in boot so the UI has swap \
headroom on low-memory configurations."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://odroid-zram.sh \
    file://odroid-zram.service \
"

S = "${UNPACKDIR}"

inherit systemd

RDEPENDS:${PN} = "kernel-module-zram"

SYSTEMD_SERVICE:${PN} = "odroid-zram.service"

do_install() {
    install -Dm0755 ${UNPACKDIR}/odroid-zram.sh \
        ${D}${bindir}/odroid-zram.sh
    install -Dm0644 ${UNPACKDIR}/odroid-zram.service \
        ${D}${systemd_system_unitdir}/odroid-zram.service
}

FILES:${PN} += " \
    ${bindir}/odroid-zram.sh \
    ${systemd_system_unitdir}/odroid-zram.service \
"
