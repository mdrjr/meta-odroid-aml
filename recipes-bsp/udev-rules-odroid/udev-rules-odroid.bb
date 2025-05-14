DESCRIPTION = "ODROID udev rules"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


SRC_URI = " \
        file://99-odroid.rules \
"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install() {
        install -d ${D}${nonarch_base_libdir}/udev/rules.d
        install -m 0644 ${UNPACKDIR}/99-odroid.rules ${D}${nonarch_base_libdir}/udev/rules.d/99-odroid.rules
}
