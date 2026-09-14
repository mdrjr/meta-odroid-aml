SUMMARY = "ODROID-C5 HDMI audio routing"
DESCRIPTION = "Points the PulseAudio default sink at the HDMI PCM (hw:0,2)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://odroid-c5-audio-routing.pa"

S = "${UNPACKDIR}"

RDEPENDS:${PN} = "pulseaudio-server"

do_install() {
    install -Dm0644 ${UNPACKDIR}/odroid-c5-audio-routing.pa \
        ${D}${sysconfdir}/pulse/default.pa.d/odroid-c5-audio-routing.pa
}

COMPATIBLE_MACHINE = "odroid-c5"
