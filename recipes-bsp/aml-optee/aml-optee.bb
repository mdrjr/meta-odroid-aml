SUMMARY = "Amlogic OP-TEE client (tee-supplicant)"
DESCRIPTION = "Prebuilt Amlogic OP-TEE user-space"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "odroid-c5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://aml-optee.tar.gz \
    file://tee-supplicant.init \
"
S = "${UNPACKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "tee-supplicant"
INITSCRIPT_PARAMS = "start 08 S . stop 20 0 1 6 ."

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

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${UNPACKDIR}/tee-supplicant.init ${D}${sysconfdir}/init.d/tee-supplicant
}

FILES:${PN} += "${bindir}/tee-supplicant ${libdir}/lib*"
FILES:${PN}-dev = ""
