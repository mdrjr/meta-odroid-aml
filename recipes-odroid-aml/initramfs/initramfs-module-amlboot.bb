SUMMARY = "Initramfs module: modprobe the ODROID-C5 Amlogic boot drivers"
DESCRIPTION = "The Amlogic SoC drivers carry"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "odroid-c5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://80-amlboot"
S = "${UNPACKDIR}"

do_install() {
    install -d ${D}/init.d
    install -m 0755 ${UNPACKDIR}/80-amlboot ${D}/init.d/80-amlboot
}

FILES:${PN} = "/init.d/80-amlboot"

RDEPENDS:${PN} = " \
    initramfs-framework-base \
    kernel-module-amlogic-debug-iotrace \
    kernel-module-amlogic-hwspinlock \
    kernel-module-amlogic-debug \
    kernel-module-amlogic-secmon \
    kernel-module-amlogic-cpuinfo \
    kernel-module-user-fault \
    kernel-module-amlogic-memory-debug \
    kernel-module-clk-scmi \
    kernel-module-amlogic-clk \
    kernel-module-amlogic-clk-soc-s7d \
    kernel-module-amlogic-gpio \
    kernel-module-amlogic-pinctrl-soc-s7d \
    kernel-module-amlogic-mailbox \
    kernel-module-amlogic-pwm \
    kernel-module-gpio-regulator \
    kernel-module-amlogic-reset \
    kernel-module-amlogic-power \
    kernel-module-amlogic-cpufreq \
    kernel-module-amlogic-efuse-unifykey \
    kernel-module-amlogic-thermal \
    kernel-module-amlogic-mmc \
    kernel-module-system-heap \
    kernel-module-amlogic-usb \
"
