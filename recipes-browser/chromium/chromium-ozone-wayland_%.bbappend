FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-add-NM12-to-linux-renderable-fourccs.patch"

# HEVC hardware decode on meson-vdec:
#  0002 - advertise stateful V4L2_PIX_FMT_HEVC profiles to the renderer
#  0003 - route HEVC through the stateful decoder's direct-feed path
SRC_URI += "file://0002-v4l2-add-stateful-HEVC-to-profile-CID-map.patch"
SRC_URI += "file://0003-v4l2-enable-hevc-in-stateful-decoder.patch"

PACKAGECONFIG ??= "use-egl use-v4l2 use-linux-v4l2 proprietary-codecs"
PACKAGECONFIG[use-linux-v4l2] = "use_v4l2_codec=true use_v4lplugin=true use_linux_v4l2_only=true"

GN_ARGS:append = " fatal_linker_warnings=false"

# Switch to ANGLE, since the newer ozone requires passthrough command decoder.
# See:
# https://issues.chromium.org/issues/40135856
CHROMIUM_EXTRA_ARGS:remove = "--use-gl=egl"
CHROMIUM_EXTRA_ARGS:append = " --use-gl=angle --use-angle=gles-egl --use-cmd-decoder=passthrough"

CHROMIUM_EXTRA_ARGS:append = " --no-sandbox --gpu-sandbox-start-early --ignore-gpu-blacklist --ignore-gpu-blocklist --enable-accelerated-video-decode"

# HACK: VDA and VEA might depend on Vaapi on linux in some old versions.
# CHROMIUM_EXTRA_ARGS:append = " --enable-features=VaapiVideoDecoder,VaapiVideoEncoder"

CHROMIUM_EXTRA_ARGS:append = " --enable-features=AcceleratedVideoEncoder,AcceleratedVideoDecoder,AcceleratedVideoDecodeLinuxGL,AcceleratedVideoDecodeLinuxZeroCopyGL"

CHROMIUM_EXTRA_ARGS:append = " --enable-wayland-ime"

python() {
    if d.getVar('MACHINE') in ('odroid-c4', 'odroid-n2', 'odroid-n2plus', 'odroid-n2l'):
        d.appendVar('GN_ARGS', ' enable_dav1d_decoder=false')
        d.setVar('PACKAGE_ARCH', d.getVar('MACHINE_ARCH'))
}
