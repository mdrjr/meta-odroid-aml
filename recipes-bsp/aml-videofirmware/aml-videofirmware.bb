SUMMARY = "Amlogic OP-TEE secure video firmware preload"
DESCRIPTION = "Prebuilt Amlogic tee_preload_fw helper"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "odroid-c5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://aml-videofirmware.tar.gz \
    file://videoFirmwarePreload.init \
"
S = "${UNPACKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "videoFirmwarePreload"
INITSCRIPT_PARAMS = "start 09 S ."

RDEPENDS:${PN} += "aml-optee odroid-c5-firmware"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} = "already-stripped"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/bin/tee_preload_fw ${D}${bindir}/

    install -d ${D}${nonarch_base_libdir}/optee_armtz
    install -m 0644 ${UNPACKDIR}/lib/optee_armtz/*.ta ${D}${nonarch_base_libdir}/optee_armtz/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${UNPACKDIR}/videoFirmwarePreload.init ${D}${sysconfdir}/init.d/videoFirmwarePreload
}

FILES:${PN} += " \
    ${bindir}/tee_preload_fw \
    ${nonarch_base_libdir}/optee_armtz \
"
