SUMMARY = "Linux kernel for ODROID-C4 (tobetter)"
LICENSE = "GPL-2.0-only"

require recipes-kernel/linux/linux-yocto.inc

inherit local-git

LINUX_VERSION ?= "7.0"
LINUX_VERSION_EXTENSION ?= "-odroid-c4"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "edf70e91a07c79e231294ebf9e0c6cd39ec5465d"
SRC_URI = "git://github.com/tobetter/linux;protocol=https;nobranch=1;branch=odroid-7.0.y"

S = "${UNPACKDIR}/${BP}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "odroid-c4"

DEPENDS:append = " openssl-native lz4-native python3-native"

do_kernel_configcheck[noexec] = "1"

INSANE_SKIP:${PN}-src += "buildpaths"
