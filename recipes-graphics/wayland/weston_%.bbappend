SRCREV = "${AUTOREV}"

SRC_URI:remove = "https://gitlab.freedesktop.org/wayland/weston/-/releases/${PV}/downloads/${BPN}-${PV}.tar.xz2"
SRC_URI:append = "git://github.com/mdrjr/weston;protocol=https;nobranch=1;branch=aml-13_0_3"

S = "${WORKDIR}/git"
