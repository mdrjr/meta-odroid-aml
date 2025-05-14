SUMMARY = "ODROID-RK autostart configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://autostart.cfg"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

inherit kernel-arch deploy nopackages

do_deploy() { 
    install -d ${DEPLOYDIR}
    install -m 0644 autostart.cfg "${DEPLOYDIR}"/autostart.cfg
}

addtask do_deploy after do_compile before do_build
