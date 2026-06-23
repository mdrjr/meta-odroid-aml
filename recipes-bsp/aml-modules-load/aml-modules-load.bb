SUMMARY = "Amlogic multimedia module autoload + media sysfs setup (ODROID-C5)"
DESCRIPTION = "Boot-time load of the Amlogic VDEC/encoder + sound codec modules"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "odroid-c5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = " \
    file://multimedia-amlogic.conf \
    file://sound-amlogic.conf \
    file://amlogic-media-sysfs \
    file://aml-modules-load.init \
"

S = "${UNPACKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "aml-modules-load"
INITSCRIPT_PARAMS = "start 15 S ."

do_install() {
    install -d ${D}${sysconfdir}/modules-load.d
    install -m 0644 ${UNPACKDIR}/multimedia-amlogic.conf ${D}${sysconfdir}/modules-load.d/
    install -m 0644 ${UNPACKDIR}/sound-amlogic.conf ${D}${sysconfdir}/modules-load.d/

    install -d ${D}${sbindir}
    install -m 0755 ${UNPACKDIR}/amlogic-media-sysfs ${D}${sbindir}/amlogic-media-sysfs

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${UNPACKDIR}/aml-modules-load.init ${D}${sysconfdir}/init.d/aml-modules-load
}

FILES:${PN} = "${sysconfdir}/modules-load.d ${sbindir}/amlogic-media-sysfs ${sysconfdir}/init.d/aml-modules-load"

RDEPENDS:${PN} += " \
    kernel-module-amvdec-ports \
    kernel-module-encoder \
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
