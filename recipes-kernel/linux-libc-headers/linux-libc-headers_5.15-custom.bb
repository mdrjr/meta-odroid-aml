require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit local-git

SRCREV = "601391d13b7a9e1a55f43e423af04662278f020a"

SRC_URI = " \
    git://github.com/hardkernel/linux.git;protocol=https;nobranch=1;branch=odroids7d-5.15.y; \
"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

