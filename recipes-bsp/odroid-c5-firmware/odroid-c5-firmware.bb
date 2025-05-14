SUMMARY = "ODROID-C5 Firmware files for Amlogic SoC"
LICENSE = "CLOSED"

SRC_URI = "file://aucpu_fw.bin \
    file://video_ucode.bin \
"

S = "${WORKDIR}/sources" 
UNPACKDIR = "${S}"

do_install:append() { 
    install -d ${D}/lib/firmware/video    
    install -Dm660 ${UNPACKDIR}/aucpu_fw.bin ${D}/lib/firmware
    install -Dm660 ${UNPACKDIR}/video_ucode.bin ${D}/lib/firmware/video
}

FILES:${PN} += "*"
