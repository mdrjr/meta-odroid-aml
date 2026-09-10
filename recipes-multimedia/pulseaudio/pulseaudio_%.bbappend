FILESEXTRAPATHS:prepend := "${THISDIR}/pulseaudio:"

SRC_URI += "file://pulseaudio.init"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME:${PN}-server = "pulseaudio"
INITSCRIPT_PARAMS:${PN}-server = "start 8 5 . stop 20 0 1 6 ."

do_install:append() {
    install -Dm755 ${UNPACKDIR}/pulseaudio.init ${D}${sysconfdir}/init.d/pulseaudio
}

FILES:${PN}-server += "${sysconfdir}/init.d/pulseaudio"
