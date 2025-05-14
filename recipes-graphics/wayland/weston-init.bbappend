FILESEXTRAPATHS:prepend := "${THISDIR}/weston-init:"

SRC_URI += "file://weston.ini \
	    file://weston-start"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh --user-group -G audio,video,input,render,wayland weston"
