SUMMARY = "ODROID-C4 HDMI audio routing"
DESCRIPTION = "Wires the Amlogic axg-sound-card DAPM mux at boot so audio \
reaches the HDMI transmitter."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://odroid-c4-audio-routing.sh \
    file://odroid-c4-audio-routing.service \
"

S = "${UNPACKDIR}"

inherit systemd

RDEPENDS:${PN} = "alsa-utils"

SYSTEMD_SERVICE:${PN} = "odroid-c4-audio-routing.service"

do_install() {
    install -Dm0755 ${UNPACKDIR}/odroid-c4-audio-routing.sh \
        ${D}${bindir}/odroid-c4-audio-routing.sh
    install -Dm0644 ${UNPACKDIR}/odroid-c4-audio-routing.service \
        ${D}${systemd_system_unitdir}/odroid-c4-audio-routing.service
}

FILES:${PN} += " \
    ${bindir}/odroid-c4-audio-routing.sh \
    ${systemd_system_unitdir}/odroid-c4-audio-routing.service \
"

COMPATIBLE_MACHINE = "odroid-c4"
