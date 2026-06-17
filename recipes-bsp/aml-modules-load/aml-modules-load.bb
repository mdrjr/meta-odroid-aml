SUMMARY = "Amlogic multimedia kernel module autoload (ODROID-C5)"
DESCRIPTION = "Boot-time autoload config for the Amlogic VDEC/encoder and sound \
codec backend modules."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "odroid-c5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://multimedia-amlogic.conf \
    file://sound-amlogic.conf \
    file://amvdec_ports.conf \
"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/modules-load.d
    install -m 0644 ${UNPACKDIR}/multimedia-amlogic.conf ${D}${sysconfdir}/modules-load.d/
    install -m 0644 ${UNPACKDIR}/sound-amlogic.conf ${D}${sysconfdir}/modules-load.d/
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${UNPACKDIR}/amvdec_ports.conf ${D}${sysconfdir}/modprobe.d/
}

FILES:${PN} = "${sysconfdir}/modules-load.d ${sysconfdir}/modprobe.d"

RDEPENDS:${PN} += " \
    kernel-module-amlogic-dvb-demux \
    kernel-module-encoder-common \
    kernel-module-decoder-common \
    kernel-module-media-sync \
    kernel-module-jpegenc \
    kernel-module-amvdec-mmjpeg \
    kernel-module-amvdec-h264mvc \
    kernel-module-amvdec-h265 \
    kernel-module-amvdec-mmpeg4 \
    kernel-module-amvdec-mavs \
    kernel-module-amvdec-mmpeg12 \
    kernel-module-amvdec-vp9-fb \
    kernel-module-amvdec-mh264 \
    kernel-module-amvdec-av1-fb \
    kernel-module-amvdec-vc1 \
    kernel-module-amvdec-debug-port \
    kernel-module-amvdec-vp9 \
    kernel-module-amvdec-avs3 \
    kernel-module-amvdec-avs2-fb \
    kernel-module-amvdec-h265-fb \
    kernel-module-amvdec-av1 \
    kernel-module-amvdec-avs2 \
    kernel-module-amvdec-mmjpeg-v4l \
    kernel-module-amvdec-h265-v4l \
    kernel-module-amvdec-mmpeg4-v4l \
    kernel-module-amvdec-mavs-v4l \
    kernel-module-amvdec-av1-t5d-v4l \
    kernel-module-amvdec-mmpeg12-v4l \
    kernel-module-amvdec-vp9-fb-v4l \
    kernel-module-amvdec-mh264-v4l \
    kernel-module-amvdec-av1-fb-v4l \
    kernel-module-amvdec-vc1-v4l \
    kernel-module-amvdec-vp9-v4l \
    kernel-module-amvdec-avs3-v4l \
    kernel-module-amvdec-avs2-fb-v4l \
    kernel-module-amvdec-h265-fb-v4l \
    kernel-module-amvdec-av1-v4l \
    kernel-module-amvdec-avs2-v4l \
    kernel-module-amlogic-snd-codec-dummy \
    kernel-module-amlogic-snd-codec-t9015 \
"
