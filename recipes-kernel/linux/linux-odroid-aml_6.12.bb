# Copyright (C) 2025, Hardkernel Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-odroid-aml.inc

inherit local-git

SRCREV_FORMAT = "mm_cd"
SRCREV = "9ea2aa831a585955dcb0e38a0bc3c63d2c690adc"
SRCREV_mm = "c01b88372fc756942d72a49c2c5be9b73aa7ad83"
SRCREV_cd = "34daba993d84e4aa09afbe3e2678dd19978eaace"

SRC_URI = " \
	git://github.com/hardkernel/linux.git;protocol=https;nobranch=1;branch=odroids7d-6.12.y; \
	git://github.com/hardkernel/yocto-platform-hardware-amlogic-media_modules.git;protocol=https;nobranch=1;branch=odroids7d-6.12.y;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/media_modules;name=mm \
	git://github.com/hardkernel/kernel_common_drivers.git;protocol=https;nobranch=1;branch=odroids7d-6.12.y;destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/common_drivers;name=cd \
	file://0001-add-realtek-wifi-vendor-driver.patch \
	file://0003-patch-realtek-vendor-driver-to-support-amlogic-kerne.patch \
	file://increase-heap-gfx-for-4k-support.fix \
	file://0005-ODROID-C5-default-output-queue-sizeimage.fix \
	file://0006-rtl8821-fix-6.12-kernel-api.fix \
    	file://defconfig \
    	file://rtl.cfg \
    	file://initramfs-boot.cfg \
"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "6.12"

do_patch:append() {
    srcfix="${TMPDIR}/work/${MULTIMACH_TARGET_SYS}/${PN}/${EXTENDPE}${PV}/sources"

    # GCC-14: add -Wno-enum-int-mismatch (only once)
    if ! grep -q -- '-Wno-enum-int-mismatch' ${S}/common_drivers/drivers/media/di_multi/Makefile; then
        sed -i '0,/ccflags-y += -I./ s/ccflags-y += -I./ccflags-y += -I. -Wno-enum-int-mismatch/g' ${S}/common_drivers/drivers/media/di_multi/Makefile
    fi

    if ! grep -q 'CONFIG_RTL8821CU' ${S}/drivers/net/wireless/Makefile; then
        printf 'obj-$(CONFIG_RTL8821CU) += rtl8821cu/\nobj-$(CONFIG_RTL8821AU) += rtl8821au/\n' >> ${S}/drivers/net/wireless/Makefile
    fi
    if ! grep -q 'rtl8821cu/Kconfig' ${S}/drivers/net/wireless/Kconfig; then
        sed -i '\#source "drivers/net/wireless/zydas/Kconfig"#a source "drivers/net/wireless/rtl8821cu/Kconfig"\nsource "drivers/net/wireless/rtl8821au/Kconfig"' ${S}/drivers/net/wireless/Kconfig
    fi

    find ${S}/drivers/net/wireless/rtl8821au ${S}/drivers/net/wireless/rtl8821cu \
        -type f \( -name Makefile -o -name '*.mk' \) \
        -exec sed -i 's#$(srctree)/$(src)#$(src)#g' {} +

    for _mk in ${S}/drivers/net/wireless/rtl8821au/Makefile ${S}/drivers/net/wireless/rtl8821cu/Makefile; do
        grep -q 'Wno-empty-body' "$_mk" || echo 'EXTRA_CFLAGS += -Wno-empty-body' >> "$_mk"
    done

    if ! patch -p0 -R --dry-run -f -d ${S} < ${srcfix}/increase-heap-gfx-for-4k-support.fix >/dev/null 2>&1; then
        patch -p0 -d ${S} < ${srcfix}/increase-heap-gfx-for-4k-support.fix
    fi

    if ! patch -p1 -R --dry-run -f -d ${S} < ${srcfix}/0004-ODROID-C5-fix-mediaproxy-producer-session-UAF.fix >/dev/null 2>&1; then
        patch -p1 -d ${S} < ${srcfix}/0004-ODROID-C5-fix-mediaproxy-producer-session-UAF.fix
    fi

    if ! patch -p1 -R --dry-run -f -d ${S} < ${srcfix}/0005-ODROID-C5-default-output-queue-sizeimage.fix >/dev/null 2>&1; then
        patch -p1 -d ${S} < ${srcfix}/0005-ODROID-C5-default-output-queue-sizeimage.fix
    fi

    if ! patch -p1 -R --dry-run -f -d ${S} < ${srcfix}/0006-rtl8821-fix-6.12-kernel-api.fix >/dev/null 2>&1; then
        patch -p1 -d ${S} < ${srcfix}/0006-rtl8821-fix-6.12-kernel-api.fix
    fi
}

do_copy_dtb() { 
	cp -f ${B}/common_drivers/arch/arm64/boot/dts/amlogic/s7d_s905x5m_odroidc5.dtb ${B}/arch/arm64/boot/
}
addtask copy_dtb before do_install after do_compile

do_kernel_configcheck[noexec] = "1"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"


MACHINE_EXTRA_RDEPENDS += " \
        kernel-module-8021q \
        kernel-module-aml-drm \
        kernel-module-aml-media \
        kernel-module-amlogic-adc \
        kernel-module-amlogic-audio-utils \
        kernel-module-amlogic-clk-soc-s4 \
        kernel-module-amlogic-clk-soc-s5 \
        kernel-module-amlogic-clk-soc-s7 \
        kernel-module-amlogic-clk-soc-sc2 \
        kernel-module-amlogic-clk-soc-t3 \
        kernel-module-amlogic-clk-soc-t3x \
        kernel-module-amlogic-clk-soc-t5m \
        kernel-module-amlogic-clk-soc-t5w \
        kernel-module-amlogic-clk-soc-t7 \
        kernel-module-amlogic-crypto-dma \
        kernel-module-amlogic-dvb \
        kernel-module-amlogic-dvb-ci \
        kernel-module-amlogic-i2c \
        kernel-module-amlogic-irblaster \
        kernel-module-amlogic-jtag \
        kernel-module-amlogic-led \
        kernel-module-amlogic-pcie \
        kernel-module-amlogic-pinctrl-soc-g12a \
        kernel-module-amlogic-pinctrl-soc-s4 \
        kernel-module-amlogic-pinctrl-soc-s5 \
        kernel-module-amlogic-pinctrl-soc-s7 \
        kernel-module-amlogic-pinctrl-soc-sc2 \
        kernel-module-amlogic-pinctrl-soc-t3 \
        kernel-module-amlogic-pinctrl-soc-t3x \
        kernel-module-amlogic-pinctrl-soc-t5m \
        kernel-module-amlogic-pinctrl-soc-t5w \
        kernel-module-amlogic-pinctrl-soc-t7 \
        kernel-module-amlogic-pinctrl-soc-tm2 \
        kernel-module-amlogic-rng \
        kernel-module-amlogic-seckey \
        kernel-module-amlogic-snd-codec-ad82128 \
        kernel-module-amlogic-snd-codec-ad82584f \
        kernel-module-amlogic-snd-codec-dummy \
        kernel-module-amlogic-snd-codec-pa1 \
        kernel-module-amlogic-snd-codec-sy6026l \
        kernel-module-amlogic-snd-codec-t9015 \
        kernel-module-amlogic-snd-codec-tas5707 \
        kernel-module-amlogic-snd-codec-tas5805 \
        kernel-module-amlogic-snd-codec-tl1 \
        kernel-module-amlogic-snd-soc \
        kernel-module-amlogic-spi \
        kernel-module-amlogic-tee \
        kernel-module-amlogic-usb \
        kernel-module-amlogic-usb-cam \
        kernel-module-amlogic-watchdog \
        kernel-module-amlogic-wireless \
        kernel-module-aml-smmu \
        kernel-module-aml-watermark \
        kernel-module-amvdec-debug-port \
        kernel-module-aqc111 \
        kernel-module-asix \
        kernel-module-ax88179-178a \
        kernel-module-bluetooth \
        kernel-module-bsd-comp \
        kernel-module-btbcm \
        kernel-module-btqca \
        kernel-module-btsdio \
        kernel-module-cdc-acm \
        kernel-module-cdc-eem \
        kernel-module-cdc-ether \
        kernel-module-cdc-ncm \
        kernel-module-cfg80211 \
        kernel-module-decoder-common \
        kernel-module-dvb-core \
        kernel-module-firmware \
        kernel-module-ftdi-sio \
        kernel-module-hci-uart \
        kernel-module-hidp \
        kernel-module-kheaders \
        kernel-module-ledtrig-activity \
        kernel-module-ledtrig-backlight \
        kernel-module-ledtrig-gpio \
        kernel-module-ledtrig-oneshot \
        kernel-module-ledtrig-timer \
        kernel-module-libarc4 \
        kernel-module-mac80211 \
        kernel-module-mac802154 \
        kernel-module-mali-kbase \
        kernel-module-media-clock \
        kernel-module-media-sync \
        kernel-module-nhc-dest \
        kernel-module-nhc-fragment \
        kernel-module-nhc-hop \
        kernel-module-nhc-ipv6 \
        kernel-module-nhc-mobility \
        kernel-module-nhc-routing \
        kernel-module-nhc-udp \
        kernel-module-optee \
        kernel-module-ppp-deflate \
        kernel-module-ppp-generic \
        kernel-module-ppp-mppe \
        kernel-module-pppox \
        kernel-module-pptp \
        kernel-module-pts-server \
        kernel-module-r8152 \
        kernel-module-r8153-ecm \
        kernel-module-rfcomm \
        kernel-module-rtl8150 \
        kernel-module-sha1-ce \
        kernel-module-sha1-generic \
        kernel-module-slcan \
        kernel-module-slhc \
        kernel-module-spidev \
        kernel-module-stream-input \
        kernel-module-tee \
        kernel-module-tipc \
        kernel-module-usbnet \
        kernel-module-vcan \
        kernel-module-video-framerate-adapter \
        kernel-module-zram \
        kernel-module-zsmalloc \
	kernel-module-8821cu \
	kernel-module-8821au \
"
