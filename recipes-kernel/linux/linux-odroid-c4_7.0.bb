SUMMARY = "Linux kernel for ODROID-C4 (tobetter)"
LICENSE = "GPL-2.0-only"

require recipes-kernel/linux/linux-yocto.inc

inherit local-git

LINUX_VERSION ?= "7.0"
LINUX_VERSION_EXTENSION ?= "-odroid-c4"
KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV_FORMAT = "default_rtl8821au_rtl8821cu_rtl8192fu"
SRCREV = "edf70e91a07c79e231294ebf9e0c6cd39ec5465d"
SRCREV_rtl8821au = "19ce01323ccaa4b47b9bfad757a6f6c74b23f289"
SRCREV_rtl8821cu = "81560f87fb964a497f5566c948ebc49cedb324b7"
SRCREV_rtl8192fu = "8e2ba34cd36cacd60b44a1de5ab2992409b6e554"

SRC_URI = " \
    git://github.com/tobetter/linux;protocol=https;nobranch=1;branch=odroid-7.0.y; \
    git://github.com/tobetter/rtl8821au.git;protocol=https;nobranch=1;branch=rtl8821au-odroid-arm64;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/drivers/net/wireless/realtek/rtl8821au;name=rtl8821au \
    git://github.com/tobetter/rtl8821cu.git;protocol=https;nobranch=1;branch=rtl8821cu-odroid-arm64;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/drivers/net/wireless/realtek/rtl8821cu;name=rtl8821cu \
    git://github.com/tobetter/rtl8192fu-dkms.git;protocol=https;nobranch=1;branch=rtl8192fu-odroid-arm64;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/drivers/net/wireless/realtek/rtl8192fu;name=rtl8192fu \
    file://defconfig \
    file://0001-codec_h264-bump-capture-dpb-margin.patch \
    file://0002-meson-vdec-implement-g_selection.patch \
"

S = "${UNPACKDIR}/${BP}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "odroid-c4"

DEPENDS:append = " openssl-native lz4-native python3-native"

do_kernel_configcheck[noexec] = "1"

INSANE_SKIP:${PN}-src += "buildpaths"
