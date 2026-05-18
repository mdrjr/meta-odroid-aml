SUMMARY = "ODROID-C5 Firmware files for Amlogic SoC"
LICENSE = "CLOSED"

SRC_URI = "file://aucpu_fw.bin \
    file://video_ucode.bin \
"

S = "${UNPACKDIR}" 

do_install:append() {
    install -d ${D}${nonarch_base_libdir}/firmware/video
    install -Dm660 ${UNPACKDIR}/aucpu_fw.bin ${D}${nonarch_base_libdir}/firmware
    install -Dm660 ${UNPACKDIR}/video_ucode.bin ${D}${nonarch_base_libdir}/firmware/video
}

FILES:${PN} += "*"
