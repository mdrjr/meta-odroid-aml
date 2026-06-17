FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:odroid-c5 = " file://80-wired.network"

do_install:append:odroid-c5() {
    install -m 0644 ${UNPACKDIR}/80-wired.network ${D}${systemd_unitdir}/network/80-wired.network
}
