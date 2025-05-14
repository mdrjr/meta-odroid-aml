SUMMARY = "ODROID-RK autostart script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://chromium-autostart"


S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install:append() {
    install -Dm755 ${UNPACKDIR}/chromium-autostart ${D}${bindir}/chromium-autostart
}

FILES:${PN} += " ${bindir}/chromium-autostart "