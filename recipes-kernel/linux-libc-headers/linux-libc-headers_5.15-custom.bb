require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit local-git

# odroid-c5 vendor 5.15 kernel headers; other aml boards use oe-core default
COMPATIBLE_MACHINE = "odroid-c5"

SRCREV = "72460c37258e2a19155949023af6081078a049db"

SRC_URI = " \
    git://github.com/hardkernel/linux.git;protocol=https;nobranch=1;branch=odroids7d-5.15.y; \
"

S = "${UNPACKDIR}/${BP}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

