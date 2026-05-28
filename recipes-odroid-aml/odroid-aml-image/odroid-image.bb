inherit image
inherit deploy
inherit core-image

IMAGE_FEATURES:append = " package-management serial-autologin-root ssh-server-openssh weston"
DEPENDS:append:odroid-c5 = " u-boot-odroid-c5 odroid-autostart-cfg"
DEPENDS:append:odroid-c4 = " u-boot-odroid-c4"
DEPENDS:append:odroid-n2 = " u-boot-odroid-n2"
DEPENDS:append:odroid-n2plus = " u-boot-odroid-n2"
DEPENDS:append:odroid-n2l = " u-boot-odroid-n2l"
IMAGE_INSTALL:append = " odroid-autostart-script weston-init"

DISPLAY_PLATFORM ?= "wayland"
DISTRO_FEATURES:append = " egl opengl wayland gbm"

IMAGE_INSTALL:append = " ncurses-terminfo os-release ifupdown dhcpcd dialog"

# Audio
IMAGE_INSTALL:append = " alsa-utils pulseaudio-server alsa-plugins-pulseaudio-conf "

# Chromium
IMAGE_INSTALL:append:odroid-c4 = " chromium-ozone-wayland"
IMAGE_INSTALL:append:odroid-c5 = " chromium-ozone-wayland"
IMAGE_INSTALL:append:odroid-n2 = " chromium-ozone-wayland"
IMAGE_INSTALL:append:odroid-n2plus = " chromium-ozone-wayland"
IMAGE_INSTALL:append:odroid-n2l = " chromium-ozone-wayland"
IMAGE_INSTALL:append = " weston v4l-utils libv4l"

# glmark2 (PACKAGECONFIG set in odroid.inc so it reaches the glmark2 recipe)
IMAGE_INSTALL:append = " glmark2"

# For WiFi
IMAGE_INSTALL:append = " \
	wpa-supplicant \
"

# Misc
IMAGE_INSTALL:append:odroid-c5 = " odroid-c5-firmware"
# Meson VDEC firmware blobs for hw video decode (sm1_vp9_mmu, g12a_h264, gxl_mpeg12)
IMAGE_INSTALL:append:odroid-c4 = " linux-firmware-amlogic-vdec"
IMAGE_INSTALL:append:odroid-n2 = " linux-firmware-amlogic-vdec"
IMAGE_INSTALL:append:odroid-n2plus = " linux-firmware-amlogic-vdec"
IMAGE_INSTALL:append:odroid-n2l = " linux-firmware-amlogic-vdec"
IMAGE_INSTALL:append = " \
        udev-rules-odroid \
        kernel-modules \
	bash \
	iproute2 \
        initscripts \
        packagegroup-core-boot \
        openssl \
        net-tools \
        ntp-utils \
        avahi-daemon \
        ca-certificates \
        e2fsprogs-e2fsck \
        e2fsprogs-mke2fs \
        e2fsprogs-tune2fs \
        glibc-binary-localedata-en-gb \
        nfs-utils-client \
        openssh-sftp \
        openssh-sftp-server \
        opkg \
        packagegroup-base \
        packagegroup-core-boot \
        packagegroup-core-weston \
        parted \
        nfs-utils \
        sdparm \
        tzdata \
"

python() { 
    if d.getVar('DEPENDS').find('u-boot-odroid-c5') != -1:
        d.appendVar('BOOT_FILES', ' boot.scr ')
        d.appendVar('IMAGE_BOOT_FILES', ' boot.scr ')

    if d.getVar('DEPENDS').find('odroid-autostart') != -1:
        d.appendVar('BOOT_FILES', ' autostart.cfg ')
        d.appendVar('IMAGE_BOOT_FILES', ' autostart.cfg ')

}
