FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-add-NM12-to-linux-renderable-fourccs.patch"
SRC_URI += "file://0002-v4l2-add-stateful-HEVC-to-profile-CID-map.patch"
SRC_URI += "file://0003-v4l2-enable-hevc-in-stateful-decoder.patch"

SRC_URI:remove:odroid-c5 = " \
    file://0001-add-NM12-to-linux-renderable-fourccs.patch \
    file://0002-v4l2-add-stateful-HEVC-to-profile-CID-map.patch \
"

# ODROID-C5-specific chromium patches 
SRC_URI:append:odroid-c5 = " \
    file://0001-ODROID-C5-Enable-Hardware-Acceleration-Decoder.patch \
    file://0002-ODROID-C5-Fix-GPU-Process-Crash-During-Config-Change.patch \
    file://0003-ODROID-C5-Fix-Screen-Glitches-in-Fullscreen-Mode-on-.patch \
    file://0004-ODROID-C5-Fix-YouTube-Video-Looping.patch \
    file://0005-ODROID-C5-force-legacy-v4l2-decoder-selection.patch \
"

PACKAGECONFIG ??= "use-egl use-v4l2 use-linux-v4l2 proprietary-codecs"
PACKAGECONFIG[use-linux-v4l2] = "use_v4l2_codec=true use_v4lplugin=true use_linux_v4l2_only=true"

GN_ARGS:append = " fatal_linker_warnings=false"

# Switch to ANGLE, since the newer ozone requires passthrough command decoder.
# See:
# https://issues.chromium.org/issues/40135856
CHROMIUM_EXTRA_ARGS:remove = "--use-gl=egl"
CHROMIUM_EXTRA_ARGS:append = " --use-gl=angle --use-angle=gles-egl --use-cmd-decoder=passthrough"

CHROMIUM_EXTRA_ARGS:append = " --no-sandbox --gpu-sandbox-start-early --ignore-gpu-blacklist --ignore-gpu-blocklist --enable-accelerated-video-decode"

CHROMIUM_EXTRA_ARGS:append = " --enable-features=AcceleratedVideoEncoder,AcceleratedVideoDecoder,AcceleratedVideoDecodeLinuxGL,AcceleratedVideoDecodeLinuxZeroCopyGL"

CHROMIUM_EXTRA_ARGS:append = " --enable-wayland-ime"

python() {
    if d.getVar('MACHINE') in ('odroid-c4', 'odroid-n2', 'odroid-n2plus', 'odroid-n2l'):
        d.appendVar('GN_ARGS', ' enable_dav1d_decoder=false')
        d.setVar('PACKAGE_ARCH', d.getVar('MACHINE_ARCH'))
}

# C5 (S905X5M) has an AV1 HW decoder. Upstream gates the V4L2 AV1 code paths on
# USE_AV1_HW_DECODER, which defaults off for V4L2 (Linux) builds (it only turns on
# for is_chromeos / is_linux+use_vaapi). Force it on so the ODROID-C5 chromium
# patches' AV1 plumbing is actually compiled in.
GN_ARGS:append:odroid-c5 = " use_av1_hw_decoder=true"
