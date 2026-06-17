FILESEXTRAPATHS:prepend := "${THISDIR}/weston-init:"

SRC_URI += "file://weston.ini \
	    file://weston-start \
	    file://weston-network-online.conf"

# Gate the compositor + chromium kiosk on the network being online so the
# remote URL loads on the first try.
do_install:append() {
    install -d ${D}${systemd_system_unitdir}/weston.service.d
    install -m 0644 ${UNPACKDIR}/weston-network-online.conf ${D}${systemd_system_unitdir}/weston.service.d/10-network-online.conf
}

FILES:${PN} += "${systemd_system_unitdir}/weston.service.d"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh --user-group -G audio,video,input,render,wayland,seat weston"
