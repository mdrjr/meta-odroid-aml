SUMMARY = "ODROID-C4 U-Boot Pre-Built Binary"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "file://u-boot.bin"

S = "${UNPACKDIR}"

inherit deploy nopackages

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${UNPACKDIR}/u-boot.bin "${DEPLOYDIR}/u-boot.bin"
}

addtask do_deploy before do_build

ALLOW_EMPTY:${PN} = "1"
COMPATIBLE_MACHINE = "odroid-c4"
