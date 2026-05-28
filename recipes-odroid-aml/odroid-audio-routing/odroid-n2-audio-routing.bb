SUMMARY = "ODROID-N2 audio routing"
DESCRIPTION = "Wires the Amlogic axg-sound-card DAPM mux at boot so audio \
reaches HDMI on ODROID-N2."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://odroid-n2-audio-routing.sh \
    file://odroid-n2-audio-routing.service \
"

S = "${UNPACKDIR}"

inherit systemd

RDEPENDS:${PN} = "alsa-utils"

SYSTEMD_SERVICE:${PN} = "odroid-n2-audio-routing.service"

do_install() {
    install -Dm0755 ${UNPACKDIR}/odroid-n2-audio-routing.sh \
        ${D}${bindir}/odroid-n2-audio-routing.sh
    install -Dm0644 ${UNPACKDIR}/odroid-n2-audio-routing.service \
        ${D}${systemd_system_unitdir}/odroid-n2-audio-routing.service
}

FILES:${PN} += " \
    ${bindir}/odroid-n2-audio-routing.sh \
    ${systemd_system_unitdir}/odroid-n2-audio-routing.service \
"

COMPATIBLE_MACHINE = "odroid-(n2|n2plus|n2l)"
