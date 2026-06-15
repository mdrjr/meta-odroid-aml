SUMMARY = "Amlogic OP-TEE client (tee-supplicant)"
DESCRIPTION = "Prebuilt Amlogic OP-TEE user-space"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "odroid-c5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://aml-optee.tar.gz"
S = "${UNPACKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "tee-supplicant.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} = "already-stripped dev-so"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/bin/tee-supplicant ${D}${bindir}/

    install -d ${D}${libdir}
    cp -a --no-preserve=ownership ${UNPACKDIR}/lib/. ${D}${libdir}/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/tee-supplicant.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${bindir}/tee-supplicant ${libdir}/lib*"
FILES:${PN}-dev = ""
