require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit local-git

SRCREV = "72460c37258e2a19155949023af6081078a049db"

SRC_URI = " \
    git://github.com/hardkernel/linux.git;protocol=https;nobranch=1;branch=odroids7d-5.15.y; \
"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

