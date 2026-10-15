FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " file://0001-HACK-drm-formats-comment-out-duplicate-format-assert.patch \
                   file://0002-surface-state-Fix-assert-firing-when-subsurfaces-have-no-views.patch"

SRC_URI:append:odroid-c5 = " file://0008-gl-renderer-wait-for-the-dmabuf-s-implicit-fence-Mali-won-t.patch"
PACKAGE_ARCH:odroid-c5 = "${MACHINE_ARCH}"
