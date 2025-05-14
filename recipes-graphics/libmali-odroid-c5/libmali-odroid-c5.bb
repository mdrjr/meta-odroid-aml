# Copyright (C) 2025, Hardkernel Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)
LICENSE = "CLOSED"

SECTION = "libs"

SRC_URI = "file://tarball.tar.bin"
S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

DEPENDS = "coreutils-native libdrm"
PROVIDES:append = " virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgles3 virtual/libgbm"
DEPENDS:append = " wayland"

inherit pkgconfig autotools

do_configure[noexec] = "1"
do_compile[noexec] = "1"
# do_populate_lic[noexec] = "1"

ASNEEDED = ""

# Inject RPROVIDEs/RCONFLICTs on the generic lib name.
python __anonymous() {
    pn = d.getVar('PN')
    pn_dev = pn + "-dev"
    d.setVar("DEBIAN_NOAUTONAME:" + pn, "1")
    d.setVar("DEBIAN_NOAUTONAME:" + pn_dev, "1")

    for p in (("libegl", "libegl1"),
              ("libgles1", "libglesv1-cm1"),
              ("libgles2", "libglesv2-2"),
              ("libgles3",)):
        pkgs = " " + " ".join(p)
        d.appendVar("RREPLACES:" + pn, pkgs)
        d.appendVar("RPROVIDES:" + pn, pkgs)
        d.appendVar("RCONFLICTS:" + pn, pkgs)

        # For -dev, the first element is both the Debian and original name
        pkgs = " " + p[0] + "-dev"
        d.appendVar("RREPLACES:" + pn_dev, pkgs)
        d.appendVar("RPROVIDES:" + pn_dev, pkgs)
        d.appendVar("RCONFLICTS:" + pn_dev, pkgs)
}

do_install() {
    tar xf ${S}/tarball.tar.bin -C ${D}
}

## Package List
INSANE_SKIP:${PN} = "already-stripped ldflags dev-so textrel buildpaths"
INSANE_SKIP:${PN}-dev = "staticdev"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
RPROVIDES:${PN}:append = " libmali"

FILES:${PN}-staticdev = ""
FILES:${PN}-dev = " \
        ${includedir} \
        ${libdir}/lib*.a \
        ${libdir}/pkgconfig \
"
FILES:${PN} = "*"
