# Copyright (C) 2025, Hardkernel Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-odroid-aml.inc

inherit local-git

SRCREV_FORMAT = "mm_cd"
SRCREV = "601391d13b7a9e1a55f43e423af04662278f020a"
SRCREV_mm = "61d677d7238671960b4bf2d544d1abc1ab688be4"
SRCREV_cd = "79993cb1aa356ab5ae6e6cd16b8536f2dde5855e"

SRC_URI = " \
	git://github.com/hardkernel/linux.git;protocol=https;nobranch=1;branch=odroids7d-5.15.y; \
	git://github.com/hardkernel/yocto-platform-hardware-amlogic-media_modules.git;protocol=https;nobranch=1;branch=odroids7d-5.15.y;destsuffix=git/media_modules;name=mm \
	git://github.com/hardkernel/kernel_common_drivers.git;protocol=https;nobranch=1;branch=odroids7d-5.15.y;destsuffix=git/common_drivers;name=cd \
	file://0001-add-realtek-wifi-vendor-driver.patch \
	file://0002-yocto-kernel-defconfig.patch \
	file://0003-patch-realtek-vendor-driver-to-support-amlogic-kerne.patch \
    file://rtl.cfg \
"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.15"

# HACK: We manually patch this file to build with GCC-14
# Done to avoid forking and patching
do_patch:append() {
    sed -i '0,/ccflags-y += -I./ s/ccflags-y += -I./ccflags-y += -I. -Wno-enum-int-mismatch/g' ${S}/common_drivers/drivers/media/di_multi/Makefile
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
        kernel-module-amlogic-camera \
        kernel-module-amlogic-clk-soc-s4 \
        kernel-module-amlogic-clk-soc-s5 \
        kernel-module-amlogic-clk-soc-s7 \
        kernel-module-amlogic-clk-soc-sc2 \
        kernel-module-amlogic-clk-soc-t3 \
        kernel-module-amlogic-clk-soc-t3x \
        kernel-module-amlogic-clk-soc-t5m \
        kernel-module-amlogic-clk-soc-t5w \
        kernel-module-amlogic-clk-soc-t7 \
        kernel-module-amlogic-clk-soc-tm2 \
        kernel-module-amlogic-crypto-dma \
        kernel-module-amlogic-dvb \
        kernel-module-amlogic-dvb-ci \
        kernel-module-amlogic-dvb-demux \
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
        kernel-module-amvdec-av1 \
        kernel-module-amvdec-av1-fb \
        kernel-module-amvdec-av1-fb-v4l \
        kernel-module-amvdec-av1-t5d-v4l \
        kernel-module-amvdec-av1-v4l \
        kernel-module-amvdec-avs2 \
        kernel-module-amvdec-avs2-fb \
        kernel-module-amvdec-avs2-fb-v4l \
        kernel-module-amvdec-avs2-v4l \
        kernel-module-amvdec-avs3 \
        kernel-module-amvdec-avs3-v4l \
        kernel-module-amvdec-debug-port \
        kernel-module-amvdec-h264mvc \
        kernel-module-amvdec-h265 \
        kernel-module-amvdec-h265-fb \
        kernel-module-amvdec-h265-fb-v4l \
        kernel-module-amvdec-h265-v4l \
        kernel-module-amvdec-mavs \
        kernel-module-amvdec-mavs-v4l \
        kernel-module-amvdec-mh264 \
        kernel-module-amvdec-mh264-v4l \
        kernel-module-amvdec-mmjpeg \
        kernel-module-amvdec-mmjpeg-v4l \
        kernel-module-amvdec-mmpeg12 \
        kernel-module-amvdec-mmpeg12-v4l \
        kernel-module-amvdec-mmpeg4 \
        kernel-module-amvdec-mmpeg4-v4l \
        kernel-module-amvdec-ports \
        kernel-module-amvdec-vc1 \
        kernel-module-amvdec-vc1-v4l \
        kernel-module-amvdec-vp9 \
        kernel-module-amvdec-vp9-fb \
        kernel-module-amvdec-vp9-fb-v4l \
        kernel-module-amvdec-vp9-v4l \
        kernel-module-amvenc-multi \
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
        kernel-module-ch341 \
        kernel-module-cp210x \
        kernel-module-decoder-common \
        kernel-module-diag \
        kernel-module-drm-mipi-dbi \
        kernel-module-dvb-core \
        kernel-module-dwav-usb-mt \
        kernel-module-dwc-otg \
        kernel-module-encoder \
        kernel-module-encoder-common \
        kernel-module-firmware \
        kernel-module-ftdi-sio \
        kernel-module-hci-uart \
        kernel-module-hidp \
        kernel-module-hwmon \
        kernel-module-jpegenc \
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
        kernel-module-memalloc \
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
        kernel-module-rfkill \
        kernel-module-rtc-pcf8563 \
        kernel-module-rtl8150 \
        kernel-module-sha1-ce \
        kernel-module-sha1-generic \
        kernel-module-shtc1 \
        kernel-module-slcan \
        kernel-module-slhc \
        kernel-module-spidev \
        kernel-module-squashfs \
        kernel-module-stream-input \
        kernel-module-tee \
        kernel-module-tipc \
        kernel-module-usbnet \
        kernel-module-v4l2-async \
        kernel-module-v4l2-fwnode \
        kernel-module-vc8000 \
        kernel-module-vcan \
        kernel-module-videobuf-core \
        kernel-module-videobuf-vmalloc \
        kernel-module-video-framerate-adapter \
        kernel-module-vpu \
        kernel-module-wire \
        kernel-module-zram \
        kernel-module-zsmalloc \
		kernel-module-8821cu \
		kernel-module-8821au \
"