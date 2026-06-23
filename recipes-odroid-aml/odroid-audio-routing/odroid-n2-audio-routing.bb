SUMMARY = "ODROID-N2 audio routing"
DESCRIPTION = "Wires the Amlogic axg-sound-card DAPM mux at boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://odroid-n2-audio-routing.sh \
    file://odroid-n2-audio-routing.init \
"

S = "${UNPACKDIR}"

inherit update-rc.d

RDEPENDS:${PN} = "alsa-utils"

INITSCRIPT_NAME = "odroid-n2-audio-routing"
INITSCRIPT_PARAMS = "start 16 S ."

do_install() {
    install -Dm0755 ${UNPACKDIR}/odroid-n2-audio-routing.sh \
        ${D}${bindir}/odroid-n2-audio-routing.sh
    install -Dm0755 ${UNPACKDIR}/odroid-n2-audio-routing.init \
        ${D}${sysconfdir}/init.d/odroid-n2-audio-routing
}

FILES:${PN} += "${bindir}/odroid-n2-audio-routing.sh"

COMPATIBLE_MACHINE = "odroid-(n2|n2plus|n2l)"
