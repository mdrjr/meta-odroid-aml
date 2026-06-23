SUMMARY = "ODROID-C5 initramfs: load Amlogic platform modules, mount rootfs, switch_root"
DESCRIPTION = "Minimal initramfs for ODROID-C5."

INITRAMFS_SCRIPTS ?= " \
    initramfs-framework-base \
    initramfs-module-udev \
    initramfs-module-rootfs \
    initramfs-module-e2fs \
    initramfs-module-amlboot \
"

PACKAGE_INSTALL = "${INITRAMFS_SCRIPTS} ${VIRTUAL-RUNTIME_base-utils} udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd with rootfs features, and never embed a kernel.
IMAGE_FEATURES = ""
PACKAGE_EXCLUDE = "kernel-image-*"

IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""
LICENSE = "MIT"

COMPATIBLE_MACHINE = "odroid-c5"

INITRAMFS_FSTYPES ?= "cpio.gz"
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
