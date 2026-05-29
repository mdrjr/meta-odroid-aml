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
    file://0001-FROMLIST-v2-media-meson-vdec-Fix-memory-leak-in-erro.patch \
    file://0002-FROMLIST-v1-media-meson-vdec-esparser-check-parsing-.patch \
    file://0003-media-meson-vdec-fix-NULL-pointer-dereference-in-vde.patch \
    file://0004-media-meson-vdec-guard-codec_ops-resume-against-NULL.patch \
    file://0005-media-meson-vdec-use-fuzzy-matching-for-VIFIFO-offse.patch \
    file://0006-media-meson-vdec-propagate-frame-type-flags-to-captu.patch \
    file://0007-media-meson-vdec-check-kthread_run-return-value-in-s.patch \
    file://0008-media-meson-vdec-esparser-fix-VP9-buffer-backpressur.patch \
    file://0009-media-meson-vdec-fix-H.264-reference-buffer-DMA-leak.patch \
    file://0010-media-meson-vdec-fix-workspace-DMA-leak-on-H.264-SEI.patch \
    file://0011-media-meson-vdec-log-warning-on-recycle-buffer-alloc.patch \
    file://0012-media-meson-vdec-move-v4l2_m2m_init-from-open-to-pro.patch \
    file://0013-media-meson-vdec-esparser-move-global-state-to-per-c.patch \
    file://0014-media-meson-vdec-reject-S_FMT-when-queue-is-busy.patch \
    file://0015-media-meson-vdec-implement-VIDIOC_G_SELECTION-for-ca.patch \
    file://0016-media-meson-vdec-report-MPEG1-2-frame-type-flags-to-.patch \
    file://0017-media-meson-vdec-fix-esparser-stall-when-capture-buf.patch \
    file://0018-media-meson-vdec-add-dynamic-resolution-change-suppo.patch \
    file://0019-media-meson-vdec-update-TODO-after-MPEG1-2-resolutio.patch \
    file://0020-FROMLIST-v3-media-meson-vdec-implement-10bit-bitstre.patch \
    file://0021-FROMLIST-v3-media-meson-vdec-add-HEVC-decode-codec.patch \
    file://0022-media-meson-vdec-add-HEVC-platform-entries-for-G12A-.patch \
    file://0023-media-meson-vdec-reconcile-cherry-picked-codec-calls.patch \
    file://0024-media-meson-vdec-set-STATUS_NEEDS_RESUME-on-source-c.patch \
"

S = "${UNPACKDIR}/${BP}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "odroid-(c4|n2|n2plus|n2l)"

DEPENDS:append = " openssl-native lz4-native python3-native"

do_kernel_configcheck[noexec] = "1"

INSANE_SKIP:${PN}-src += "buildpaths"
