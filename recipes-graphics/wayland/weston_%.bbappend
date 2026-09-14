FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " file://0001-HACK-drm-formats-comment-out-duplicate-format-assert.patch \
                   file://0002-surface-state-Fix-assert-firing-when-subsurfaces-have-no-views.patch"
