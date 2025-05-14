inherit image
inherit deploy
inherit core-image

IMAGE_FEATURES:append = " package-management serial-autologin-root ssh-server-dropbear weston"
DEPENDS:append = " u-boot-odroid-c5 odroid-autostart-cfg"
IMAGE_INSTALL:append = " odroid-autostart-script weston-init"

DISPLAY_PLATFORM ?= "wayland"
DISTRO_FEATURES:append = " egl opengl wayland gbm"

IMAGE_INSTALL:append = " ncurses-terminfo os-release ifupdown dhcpcd resolvconf dialog"

# Audio
IMAGE_INSTALL:append = " alsa-utils pulseaudio-server alsa-plugins-pulseaudio-conf "

# Chromium
IMAGE_INSTALL:append = " chromium-ozone-wayland "
IMAGE_INSTALL:append = " weston v4l-utils libv4l"

# glmark2
IMAGE_INSTALL:append = " glmark2"
PACKAGECONFIG:pn-glmark2 = "wayland-gles2 drm-gles2"

# Misc
IMAGE_INSTALL:append = " \
        odroid-c5-firmware \
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
        dropbear \
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
