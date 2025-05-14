PACKAGECONFIG:class-target:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'dri3 gallium', 'osmesa', d)}"

EXTRA_OEMESON:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '-Dglx=dri', '', d)}"
